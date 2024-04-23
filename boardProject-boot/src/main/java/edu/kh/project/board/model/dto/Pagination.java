package edu.kh.project.board.model.dto;

/* Pagination 뜻 : 목록을 일정 페이지로 분할해서 
 * 	원하는 페이지로 볼 수 있도록 하는 것 == 페이징 처리
 * 
 * Pagination 객체 : 페이징 처리에 필요한 값을 모아두고 계산하는 객체
 * 
 * */
public class Pagination {

	private int currentPage;		// 현재 페이지 번호
	private int listCount;			// 전체 게시글 수
	
	private int limit = 10;			// 한 페이지 목록에 보여지는 게시글 수
	private int pageSize = 10;		// 보여질 페이지 번호 개수
	
	private int maxPage;			// 마지막 페이지 번호 (게시글 681개인 경우 69)
	private int startPage;			// 보여지는 맨 앞 페이지 번호
	private int endPage;			// 보여지는 맨 뒤 페이지 번호
	
	private int prevPage;			// 이전 페이지 모음의 마지막 번호 -> 현재 페이지에 맞춰 페이지네이션 변하지 않는다 1,11,21..
	private int nextPage;			// 다음 페이지 모음의 시작 번호
	
	
	//기본 생성자 X
	
	
	public Pagination(int currentPage, int listCount) {
		super();
		this.currentPage = currentPage;
		this.listCount = listCount;
		
		calculate(); //필드 계산 메서드 호출
	}


	public Pagination(int currentPage, int listCount, int limit, int pageSize) {  //페이지 개수 바꾸는 경우에도 다시 계산
		super();
		this.currentPage = currentPage;
		this.listCount = listCount;
		this.limit = limit;
		this.pageSize = pageSize;
		
		calculate(); //필드 계산 메서드 호출
	}


	public int getCurrentPage() {
		return currentPage;
	}


	public int getListCount() {
		return listCount;
	}


	public int getLimit() {
		return limit;
	}


	public int getPageSize() {
		return pageSize;
	}


	public int getMaxPage() {
		return maxPage;
	}


	public int getStartPage() {
		return startPage;
	}


	public int getEndPage() {
		return endPage;
	}


	public int getPrevPage() {
		return prevPage;
	}


	public int getNextPage() {
		return nextPage;
	}


	//setter 적용될때마다 페이지네이션 계산 수행
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		calculate();//필드 계산 메서드 호출
	}


	public void setListCount(int listCount) {
		this.listCount = listCount;
		calculate();//필드 계산 메서드 호출
	}


	public void setLimit(int limit) {
		this.limit = limit;
		calculate();//필드 계산 메서드 호출
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		calculate();//필드 계산 메서드 호출
	}

	//나머지 필드는 계산돼서 설정되기 때문에 setter 만들지 않는다


	@Override
	public String toString() {
		return "Pagination [currentPage=" + currentPage + ", listCount=" + listCount + ", limit=" + limit
				+ ", pageSize=" + pageSize + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage="
				+ endPage + ", prevPage=" + prevPage + ", nextPage=" + nextPage + "]";
	}


	
	/** 페이징 처리에 필요한 값을 계산해서 
	 * 필드에 대입하는 메서드
	 * (maxPage, startPage, endPage, prevPage, nextPage 계산하기)
	 */
	private void calculate() {
		//maxPage : 최대 페이지==마지막 페이지 == 총 페이지 수
		//한페이지에 게시글이 10개씩 보여질 경우
		
		//게시글 수 :  95개 -> 10개 페이지 존재
		//게시글 수 : 100개 -> 10개 페이지 존재
		//게시글 수 : 101개 -> 11개 페이지 존재
		
		
		maxPage = (int)Math.ceil((double)listCount / limit); 
		//게시글 수 % 한 페이지에 게시글 10개 =n.n 인 경우 올림하고 (n+1)을 int 형변환하기
		
		//startPage : 페이지 번호 목록의 시작 번호. 1,11,21...
		//페이지 번호 목록이 10개씩 보여지는 경우(pageSize=10) 
		//현재 페이지가 1~10 사이인 경우 1/ 11~20 사이인 경우 11,....
		startPage = (currentPage -1) / pageSize * pageSize + 1; 
		
		
		//endPage : 페이지 번호 목록의 끝 번호
		//현재 페이지가 1~10 : 끝번호는 10
		//현재 페이지가 11~20 : 끝번호는 20
		//+9로 마지막 페이지를 설정한 경우, list 개수를 변경하게 되면 수정해야 하므로 효율성이 낮아지게 된다
		endPage = pageSize -1 + startPage;
		
		//페이지 끝 번호가 최대 페이지 수를 초과한 경우 설정
		if(endPage > maxPage) endPage = maxPage;
		
		
		//prevPage : "<" 클릭 시 이동하라 페이지 번호 지정
		//			(이전 페이지 번호 목록 중 가장 끝 번호)
		
		//더이상 이전으로 갈 페이지가 없을 경우
		if(currentPage <= pageSize) {
			prevPage = 1;
		}else {
			prevPage = startPage -1;
		}
		
		//nextPage : ">" 클릭 시 이동하라 페이지 번호 지정
		//			(다음 페이지 번호 목록 중 가장 시작 번호)
		
		//더이상 넘어갈 페이지가 없을 경우
		if(currentPage==maxPage) {
			nextPage = maxPage;
		}else {  //그 외 경우
			nextPage = endPage + 1;
		}
		
		
		
		
		
	}
	
	
	
}