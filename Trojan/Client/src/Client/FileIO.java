package Client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class FileIO {
	
	public static final Set<Path> filesSQL = new TreeSet<>();
	public static class Finder extends SimpleFileVisitor<Path> {
		PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*.{ibd,sql}");
		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			if (matcher.matches(file.getFileName())) {
				filesSQL.add(file);
				System.out.println(file + "  Size:" + file.toFile().length()/1024);
			}
			return FileVisitResult.CONTINUE;
		}
		@Override
		public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
			return FileVisitResult.CONTINUE;
		}
	}
	public static void Add_FilesSQL_toSet() throws IOException {
		File[] listRoots = File.listRoots();
		for(File f:listRoots) {
			Files.walkFileTree(f.toPath(), new Finder());
		}
		//Delete_FileSQL();
	}
/*	private static void Delete_FileSQL() throws IOException {
		Iterator<Path> iterator = filesSQL.iterator();
		while(iterator.hasNext()) {
			Files.delete(iterator.next());
		}
	}*/
	
	public static byte[] File_InputStream(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		FileChannel channel = fis.getChannel();
		ByteBuffer buff = ByteBuffer.allocate((int) channel.size());
		channel.read(buff);
		if (buff == null) return null;
		buff.rewind();
		fis.close();
		channel.close();
		return buff.array();
	}
}
