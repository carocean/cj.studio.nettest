package cj.studio.nettest.be.args;

public class TMethod {
	String code;
	String name;
	String service;
	String folder;
	String creator;
	long ctime;
	private String id;
	public TMethod() {
		
	}

	public TMethod(String code, String name, String service, String folder, String creator, long ctime) {
		super();
		this.code = code;
		this.name = name;
		this.service = service;
		this.folder = folder;
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
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getFolder() {
		return folder;
	}
	public void setFolder(String folder) {
		this.folder = folder;
	}
}
