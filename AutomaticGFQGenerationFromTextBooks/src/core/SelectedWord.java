package core;

public class SelectedWord {

	private String name;
	private int index;
	private String previous_tag_1;
	private String previous_tag_2;
	private String next_tag_1;
	private String next_tag_2;
	private String tag;

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public SelectedWord(String name, int index, String previous_tag_1, String previous_tag_2, String next_tag_1,
			String next_tag_2, String tag) {
		super();
		this.name = name;
		this.index = index;
		this.previous_tag_1 = previous_tag_1;
		this.previous_tag_2 = previous_tag_2;
		this.next_tag_1 = next_tag_1;
		this.next_tag_2 = next_tag_2;
		this.tag = tag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getPrevious_tag_1() {
		return previous_tag_1;
	}

	public void setPrevious_tag_1(String previous_tag_1) {
		this.previous_tag_1 = previous_tag_1;
	}

	public String getPrevious_tag_2() {
		return previous_tag_2;
	}

	public void setPrevious_tag_2(String previous_tag_2) {
		this.previous_tag_2 = previous_tag_2;
	}

	public String getNext_tag_1() {
		return next_tag_1;
	}

	public void setNext_tag_1(String next_tag_1) {
		this.next_tag_1 = next_tag_1;
	}

	public String getNext_tag_2() {
		return next_tag_2;
	}

	public void setNext_tag_2(String next_tag_2) {
		this.next_tag_2 = next_tag_2;
	}

}
