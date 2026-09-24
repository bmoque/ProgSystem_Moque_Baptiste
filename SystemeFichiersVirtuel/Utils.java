public class Utils {

    public static int writeInt(byte[] memory, int offset, int value) {
     
        byte b3 = (byte) ((value >> 24) & 0xFF); 
        byte b2 = (byte) ((value >> 16) & 0xFF);
        byte b1 = (byte) ((value >> 8) & 0xFF);
        byte b0 = (byte) (value & 0xFF);
        memory[offset] = b3;
        memory[offset+1] = b2;
        memory[offset+2] = b1;
        memory[offset+3] = b0;

        return 4;
    }

    public static int readInt(byte[] memory, int offset) {
        return (memory[offset] & 0xFF) << 24
               | (memory[offset+1] & 0xFF) << 16
               | (memory[offset+2] & 0xFF) << 8
               | memory[offset+3]  &0xFF;
    }

    public static int writeShort(byte[] memory, int offset, short value) {

        byte b1 = (byte) ((value >> 8) & 0xFF);
        byte b0 = (byte) (value & 0xFF);
        memory[offset] = b1;
        memory[offset+1] = b0;
        return 2;
    }

    public static short readShort(byte[] memory, int offset) {
        return (short) ((memory[offset] & 0xFF) << 8
               | memory[offset+1] & 0xFF);
    }


    public static int writeLong(byte[] memory, int offset, long value) {
        byte b7 = (byte) ((value >> 56) & 0xFF); 
        byte b6 = (byte) ((value >> 48) & 0xFF);
        byte b5 = (byte) ((value >> 40) & 0xFF);
        byte b4 = (byte) ((value >> 32) & 0xFF);
        byte b3 = (byte) ((value >> 24) & 0xFF); 
        byte b2 = (byte) ((value >> 16) & 0xFF);
        byte b1 = (byte) ((value >> 8) & 0xFF);
        byte b0 = (byte) (value & 0xFF);
        memory[offset] = b7;
        memory[offset+1] = b6;
        memory[offset+2] = b5;
        memory[offset+3] = b4;
        memory[offset+4] = b3;
        memory[offset+5] = b2;
        memory[offset+6] = b1;
        memory[offset+7] = b0;
        return 8;
    }

    public static long readLong(byte[] memory, int offset) {
        return ((long) memory[offset] & 0xFF) << 56
               | ((long)memory[offset+1] & 0xFF) << 48
               | ((long)memory[offset+2] & 0xFF) << 40
               | ((long)memory[offset+3] & 0xFF) << 32
               | ((long)memory[offset+4] & 0xFF) << 24
               | ((long)memory[offset+5] & 0xFF) << 16
               | ((long)memory[offset+6] & 0xFF) << 8
               | ((long)memory[offset+7]  & 0xFF);
    }

    public static int writeString(
            byte[] memory,
            int offset,
            String str,
            int maxLength) {

        byte[] octets = str.getBytes();

        int strIndex;
        for (strIndex = 0; strIndex <  octets.length && strIndex < maxLength; strIndex++) {
            memory[offset + strIndex] = octets[strIndex];
        }

        for (int zeroIndex = strIndex; zeroIndex < maxLength; zeroIndex++) {
            memory[offset + zeroIndex] = 0;
        }
        return maxLength;
    }

    public static String readString(
            byte[] memory,
            int offset,
            int maxLength) {

        int strIndex = 0;
        StringBuilder chaine = new StringBuilder();
        while(strIndex < maxLength && memory[offset + strIndex] != 0) {
            chaine.append((char) (memory[offset + strIndex] & 0xFF));
            strIndex++;
        }

        return chaine.toString();
    }


}