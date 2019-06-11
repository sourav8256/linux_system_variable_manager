package window;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.regex.Matcher;


public class PathManager {

	String paths;
	
	String variables = "";
	
	ExecuteShellCommand com;
	
	public PathManager() {
		com = new ExecuteShellCommand();
	}
	
	void createScriptFile() {
		getClass().getResourceAsStream("Assets/bash_template");
	}
	
	public static void main(String[] args) {
		new PathManager().pushPathVariables();
	}
	
	
	// #cvt(?s)(.+?)(?s)#tvc
	
	void pushPathVariables() {
		File file = new File(System.getProperty("user.home")+"/.bashrc");
		
		String content;
		try {
			content = Files.readString(file.toPath());
			//System.out.println(content);
			
			PrintWriter backupPw = new PrintWriter(new File("/tmp/bashrc_backup.sh"));
			backupPw.print(content);
			backupPw.close();
			
			String finalContent = "export PATH=\"$PATH:"+paths+"\""+"\n\n"+variables;
			//System.out.println(finalContent.toString());
			content = createCommentTagIfNotExist(content,file);
			
			String replacedContent = content.replaceAll("#customvariables(?s)(.+?)(?s)#selbairavmotsuc", Matcher.quoteReplacement("#customvariables\n"
					+ finalContent.toString()
					+ "\n#selbairavmotsuc"));
			
			PrintWriter out = new PrintWriter(file.toPath().toString());
			out.println(replacedContent);
			out.close();
			
			String outputContent = Files.readString(file.toPath());
			System.out.println(outputContent);
			
			reset();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	String createCommentTagIfNotExist(String content,File file) {
		
		if(!content.contains("#customvariables")) {
		
			String replacedContent = content+"\n\n\n\n#customvariables \n #selbairavmotsuc";
			return replacedContent;
		
		} else {
			return content;
		}
		
		/*
		
		if(!content.contains("#customvariables")) {

			
			PrintWriter out;
			try {
				out = new PrintWriter(file.toPath().toString());
				out.println(replacedContent);
				out.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		*/

	}
	
	
	void reset() {
		paths = null;
		variables = "";
	}
	
	boolean isPathPresent(String path){
		
		String pathVar = com.executeCommand("echo $PATH");
		
		String[] arr = pathVar.split(":");
		for(int i=0;i<arr.length;i++) {
			if(arr[i].equals(path)) {
				return true;
			}
		}
		
		return false;
	}
	
	void addPath(String path) {
		if(!isPathPresent(path)) {
			//com.executeCommand("export PATH=\"$PATH:"+path+"\"");
			//com.executeCommand("ls");
			if(paths == null || paths.length() <= 0) {
				paths = path;
			} else {
				paths = paths+":"+path;
			}
		}
	}
	
	void addVariable(String var,String path) {
		//com.executeCommand("export "+var+"=\""+path+"\"");
		variables = variables+"\n"+"export "+var+"="+"\""+path+"\"";
	}
	
}
