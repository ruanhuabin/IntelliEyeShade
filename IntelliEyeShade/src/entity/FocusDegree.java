package entity;







public class FocusDegree {
	
	//检测信息的ID
	private String tid;
	
	//专注度序列
	
	private String focusValues;

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getFocusValues() {
		return focusValues;
	}

	public void setFocusValues(String focusValues) {
		this.focusValues = focusValues;
	}
	
	public FocusDegree()
	{}

	public FocusDegree(String tid, String focusValues) {
		
		this.tid = tid;
		this.focusValues = focusValues;
	}

	@Override
	public String toString() {
		return "FocusDegree [tid=" + tid + ", focusValues=" + focusValues + "]";
	}
	
	
	
	

}
