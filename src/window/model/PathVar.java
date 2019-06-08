package window.model;

public class PathVar {
	
	String vName;
	String path;
	
	public PathVar(String vName, String path) {
		super();
		this.vName = vName;
		this.path = path;
	}
	
	public String getvName() {
		return vName;
	}

	public void setvName(String vName) {
		this.vName = vName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}




}
