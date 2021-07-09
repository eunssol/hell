package news;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository
public class NewsDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession; //주입받아
	
	public List<NewsVo> selectAll(NewsVo vo) {
		return sqlSession.selectList("news.selectAll", vo); //네임스페이스.아이디 <news.xml참고>
	}
	
	public int count(NewsVo vo) {
		return sqlSession.selectOne("news.count", vo); 
	}
	
	public NewsVo detail(NewsVo vo) {
		return sqlSession.selectOne("news.detail", vo); 
	}
	
	public void updateReadcount(NewsVo vo) {
		sqlSession.selectOne("news.updateReadcount", vo); 
	}
	
	public int insert(NewsVo vo) {
		return sqlSession.insert("news.insert",vo);
	}
	public int update(NewsVo vo) {
		return sqlSession.update("news.update",vo);
	}
	public int delFilename(NewsVo vo) {
		return sqlSession.update("news.delFilename",vo);
	}
	
	
	public int delete(NewsVo vo) {
		return sqlSession.delete("news.delete",vo);
	}
	
	
	
}
