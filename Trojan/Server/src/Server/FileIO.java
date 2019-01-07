package Server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileIO {
	public static int stt = 0;
	public static void File_Writer(byte[] bytes) throws IOException {
		File file = new File("Client-" + stt + ".bat");
		try (FileOutputStream fos = new FileOutputStream(file, true);
				FileChannel channel = fos.getChannel()) {
				ByteBuffer buff = ByteBuffer.wrap(bytes);
				channel.write(buff);
			} catch (IOException e) {
				System.err.println(e);
			}
	}
}
