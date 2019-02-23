package cj.studio.nettest.be.args;

public class TService {
	String code;
	String name;
	String folder;
	String creator;
	long ctime;
	private String id;
	public TService() {
		
	}
	public TService(String code, String name,String folder, String creator, long ctime) {
		this();
		this.code = code;
		this.name = name;
		this.folder=folder;
		this.creator = creator;
		this.ctime = ctime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getFolder() {
		return folder;
	}
	public void setFolder(String folder) {
		this.folder = folder;
	}
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public long getCtime() {
		return ctime;
	}

	public void setCtime(long ctime) {
		this.ctime = ctime;
	}
}
