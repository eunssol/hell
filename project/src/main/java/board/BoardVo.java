package board;

import java.sql.Timestamp;

public class BoardVo {
	
	private int pageRow;  // 페이지당 개수
	private int startIdx; // 시작 인덱스
	private int reqPage;  //사용자가 요청한 페이지 
	private int totCount; //총개수
	private int totPage;  //총페이지수
	private int pageRange; //하단페이지 범위
	private int startPage; //시작페이지(하단)
	private int endPage;   //끝페이지(하단)
	private String stype;  //검색타입 (전체, 제목, 내용)
	private String sval;   //검색어
	private String orderby; //정렬컬럼
	private String direct; //오름차순 or 내림차순
	

	private int no;
	private String title;
	private String content;
	private Timestamp regdate;
	private int readcount;
	private String filename_org; // 사용자가 첨부한 원본파일명
	private String filename_real; // 서버에 저장된 실제파일명 
	private String isDel;
	
	//파일이 2개라면 위에 3개 1세트 더 !
//	private String filename_org; // 사용자가 첨부한 원본파일명
//	private String filename_real; // 서버에 저장된 실제파일명 
//	private String isDEl;
	
	
	
	public BoardVo() { //생성자에 초기화를 하는 이유? 값을 바꾸지 않으면 이 값으로 등장을 하게끔. 
		this.pageRow = 10;  //10으로 초기화
		this.reqPage = 1;   //1로 초기화
		this.pageRange= 10;
		this.orderby = "regdate";
		this.direct = "DESC";
	}
	
	
	



	public String getIsDel() {
		return isDel;
	}






	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}






	public String getFilename_org() {
		return filename_org;
	}



	public void setFilename_org(String filename_org) {
		this.filename_org = filename_org;
	}



	public String getFilename_real() {
		return filename_real;
	}



	public void setFilename_real(String filename_real) {
		this.filename_real = filename_real;
	}



	public String getOrderby() {
		return orderby;
	}



	public void setOrderby(String orderby) {
		if( !"".equals(orderby)) this.orderby= orderby;
	}



	public String getDirect() {
		return direct;
	}



	public void setDirect(String direct) {
		if( !"".equals(direct)) this.direct = direct;
	}



	public String getStype() {
		return stype;
	}


	public void setStype(String stype) {
		this.stype = stype;
	}


	public String getSval() {
		return sval;
	}


	public void setSval(String sval) {
		this.sval = sval;
	}


	public int getStartPage() {
		return startPage;
	}


	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}


	public int getEndPage() {
		return endPage;
	}


	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}


	public int getPageRange() {
		return pageRange;
	}


	public void setPageRange(int pageRange) {
		this.pageRange = pageRange;
	}


	public int getTotCount() {
		return totCount;
	}


	public void setTotCount(int totCount) {
		this.totCount = totCount;
	}


	public int getTotPage() {
		return totPage;
	}


	public void setTotPage(int totPage) {
		this.totPage = totPage;
	}


	public int getReqPage() {
		return reqPage;
	}

	public void setReqPage(int reqPage) {
		this.reqPage = reqPage;
	}
	
	public int getStartIdx() {
		//return startIdx;
		return (getReqPage()-1)*getPageRow();  //이해안감
	}

	public void setStartIdx(int startIdx) {
		this.startIdx = startIdx;
	}
	
	
	public int getPageRow() {
		return pageRow;
	}

	public void setPageRow(int pageRow) {
		this.pageRow = pageRow;
	}

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	
}
