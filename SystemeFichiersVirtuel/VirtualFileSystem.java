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
}