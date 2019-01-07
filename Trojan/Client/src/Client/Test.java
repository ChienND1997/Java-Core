package Client;

import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Test {

	public static void main(String[] args) {
		File[] files = File.listRoots();
		File[] listFiles = files[0].listFiles();
		Path[] paths = new Path[files.length - 1];
		IntStream.range(0, files.length - 1).forEach(i -> {
			paths[i] = files[i].toPath();
		});
		Arrays.stream(listFiles).forEach(System.out::println);
		
	}
}
