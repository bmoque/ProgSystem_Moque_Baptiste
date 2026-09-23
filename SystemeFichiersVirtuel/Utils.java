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
}