package socket;

public class Response {
	public int id;  
    public String result;
    
    public String getResponse() {
		return "id" + id + "result" + result;
	}
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	} 
}
