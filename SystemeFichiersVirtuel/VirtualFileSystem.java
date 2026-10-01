import java.util.*;

public class VirtualFileSystem {

    private MemoryManager memoryManager;

    public VirtualFileSystem() {
        this.memoryManager =
                new MemoryManager();
    }

    private int allocateInode() {

        for (int inode = 0; inode < MemoryManager.MAX_INODES; inode++) {

            Inode i = new Inode(memoryManager, inode);

            if (i.getFileType() == 0) {
                return inode;
            }
        }

        return -1;
    }

    public boolean createFile(
            String directory,
            String filename) {

        int inodeNum = allocateInode();

        if (inodeNum == -1) {
            return false;
        }
        
        
        // Construire l'inode.
        Inode inode = new Inode(memoryManager, inodeNum);

        int[] directPointers = new int[Inode.DIRECT_POINTERS]; 

        long currentTime = System.currentTimeMillis();

        // L'initialiser comme fichier vide.
        inode.writeToMemory(
            1,                  // fileType 
            0,                  // fileSize 
            currentTime,        // Date de création
            currentTime,        // Date de modification
            directPointers,     // Pointeurs direct
            0,                  // Pointeur indirect
            (short) 0644,       // Permissions 
            1                   // Nombre de liens 
        );

        return true;
    }

    public MemoryManager getMemoryManager() {
        return memoryManager;
    }


    public boolean writeFile(
            int inodeNum,
            byte[] data) {

        int blocksNeeded =
                (data.length
                + MemoryManager.BLOCK_SIZE - 1)
                / MemoryManager.BLOCK_SIZE;

        if (blocksNeeded > Inode.DIRECT_POINTERS) {
            return false;
        }

        int[] blockPointers =
                new int[Inode.DIRECT_POINTERS];

        // Allouer blocksNeeded blocs.
        boolean blockLibre = true;
        for(int i = 0; i < blocksNeeded && blockLibre; i++) {
            int blockUsed = memoryManager.allocateBlock();
            if (blockUsed != -1) {
                blockPointers[i] = blockUsed;
            }
            else {
                blockLibre = false;
            }
        }
        if (!blockLibre) {
            return false;
        }
        

        byte[] memory =
                memoryManager.getFilesystemMemory();

        int bytesRemaining =
                data.length;

        int dataSrcOffset = 0;

        // Pour chaque bloc :
        for(int i = 0; i < blocksNeeded; i++) {
            // - récupérer le numéro du bloc ;
            // - calculer son offset physique ;
            int offset = blockPointers[i] * memoryManager.BLOCK_SIZE;

            // - calculer la quantité à copier ;
            if (bytesRemaining > memoryManager.BLOCK_SIZE) {
                // - copier les données.
                System.arraycopy(data, dataSrcOffset, memory, offset, memoryManager.BLOCK_SIZE);
                bytesRemaining = bytesRemaining - memoryManager.BLOCK_SIZE;
                dataSrcOffset = dataSrcOffset + memoryManager.BLOCK_SIZE;
            } else {
                // - copier les données.
                System.arraycopy(data, dataSrcOffset, memory, offset, bytesRemaining);
            } 
        }    

        // Mettre à jour l'inode.
        Inode inode = new Inode(memoryManager,inodeNum);
        long currentTime = System.currentTimeMillis();

        inode.writeToMemory(
            1,              // fileType
            data.length,    // fileSize 
            currentTime,    // Date de création
            currentTime,    // Date de modification
            blockPointers,  // Pointeurs direct
            0,              // Pointeur indirect
            (short)644,     // Permissions 
            1               // Nombre de liens 
        );

        return true;
    } 


    public byte[] readFile(int inodeNum) {

        Inode inode =
                new Inode(memoryManager, inodeNum);

        int fileSize =
                inode.getFileSize();

        if (fileSize == 0) {
            return new byte[0];
        }

        byte[] fileData =
                new byte[fileSize];

        byte[] memory =
                memoryManager.getFilesystemMemory();

        int[] blockPointers =
                inode.getDirectPointers();      
        
        int blocksNeeded = (fileSize + MemoryManager.BLOCK_SIZE - 1) / MemoryManager.BLOCK_SIZE;
        int bytesRemaining = fileSize;
        int destOffset = 0;

        // Parcourir les blocs utilisés.
        for (int i = 0; i < blocksNeeded; i++) {
            int offset = blockPointers[i] * memoryManager.BLOCK_SIZE;
            if (bytesRemaining > memoryManager.BLOCK_SIZE) {
                // Copier chaque fragment vers fileData.
                System.arraycopy(memory, offset, fileData, destOffset, memoryManager.BLOCK_SIZE);
                bytesRemaining = bytesRemaining - memoryManager.BLOCK_SIZE;
                destOffset = destOffset + memoryManager.BLOCK_SIZE;
            } else {
                // Copier chaque fragment vers fileData.
                System.arraycopy(memory, offset, fileData, destOffset, bytesRemaining);
            }
        }

        return fileData; 
    }
}