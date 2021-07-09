package news;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements NewsService {
	@Autowired
	NewsDao dao;
	
	@Override
	public List<NewsVo> selectAll(NewsVo vo) {
		int totCount = dao.count(vo); //총개수
		// 총페이지수 
		int totPage = totCount / vo.getPageRow();
		if (totCount % vo.getPageRow() > 0) totPage++;
		//int startIdx = (vo.getReqPage()-1) * vo.getPageRow(); // 시작인덱스
		//vo.setStartIdx(startIdx); //이렇게 셋해줘야 저장완료!
		// int startIdx = (reqPage-1) * pageRow 라고 적었던 나를 반성하자
		//시작페이지
		int startPage = (vo.getReqPage()-1)/vo.getPageRange()*vo.getPageRange()+1; //제일 작은 값이 나와도 페이지 1이 나오게 하려고 +1을 해주는 거임
		int endPage = startPage+vo.getPageRange()-1;
		if (endPage > totPage) endPage = totPage; //이게 있어야 있는 페이지만큼 하단페이지버튼이 나오게.
		
		vo.setStartPage(startPage);
		vo.setEndPage(endPage);
		vo.setTotCount(totCount);
		vo.setTotPage(totPage);
		return dao.selectAll(vo);
	}

	@Override
	public NewsVo detail(NewsVo vo) {
		dao.updateReadcount(vo);
		return dao.detail(vo);
		
	}

	@Override
	public int insert(NewsVo vo) {
		
		return dao.insert(vo);
	}

	@Override
	public NewsVo edit(NewsVo vo) {
		return dao.detail(vo);
		
	}
	
	
	@Override
	public int update(NewsVo vo) {
		if ("1".equals(vo.getIsDel())) {
			dao.delFilename(vo); //1하면 빈값으로 업데이트
		}
		return dao.update(vo);
	}
	
	@Override

	public int delete(NewsVo vo) {
		return dao.delete(vo);
		
	}



}
