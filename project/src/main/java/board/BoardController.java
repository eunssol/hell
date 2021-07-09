package board;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BoardController {
	
	@Autowired
	BoardService service;
	
	@RequestMapping("/board/index.do")
	public String index(Model model, BoardVo vo) { //vo는 사용자로부터 커멘드객체로 불러올 것이야
		model.addAttribute("list", service.selectAll(vo)); //list란 이름으로 전체 데이터가 dao에 담겨서 모델에 담김(=request에 담김=스프링이 담아줌)
		/*
		 model.addAttribute("pageRow", vo.getPageRow()); model.addAttribute("reqPage",
		 vo.getReqPage());
		 */
		return "board/index"; //board에 있는 index.jsp 
	
	//디스패쳐서블릿이 list란 이름으로 ..model에 넣어줘서 request에 set해준다?

	}
	
	@RequestMapping("/board/detail.do")
	
	public String detail(Model model, BoardVo vo) {
		model.addAttribute("vo", service.detail(vo));
		return "board/detail";
	}
	
	@RequestMapping("/board/write.do")
	public String write(Model model, BoardVo vo) {
		return "board/write";
	}
	
	@RequestMapping("/board/insert.do")
	public String insert(Model model, BoardVo vo, 
							@RequestParam("file") MultipartFile file, HttpServletRequest req) {
		//service.insert(vo, filename, req)
		if( !file.isEmpty()) { //첨부파일이 있으면
			try {
				String org = file.getOriginalFilename(); //원본파일명
			
				String ext = ""; //확장자명
				
				ext = org.substring(org.lastIndexOf("."));
				String real = new Date().getTime()+ext; // 서버에 저장할 실제 파일명
	//			System.out.println("org:"+org);
	//			System.out.println("real:"+real);
				
				//파일 저장
				String path = req.getRealPath("/upload/"); //경로
		//		System.out.println(path);
				file.transferTo(new File(path+real)); //경로 + 파일명 저장
				
				//vo에 set
				vo.setFilename_org(org);
				vo.setFilename_real(real);
				
			} catch (Exception e) {
			
			}
		}
		int r =service.insert(vo);
		//r > 0 : 정상작동 -> alert -> 목록으로 이동
		//r ==0 : 비정상 -> alert -> 이전페이지로 이동
		
		if (r > 0) {
			model.addAttribute("msg", "정상적으로 등록되었습니다.");
			model.addAttribute("url", "index.do");
		} else {
			model.addAttribute("msg", "등록실패.");
			model.addAttribute("url", "write.do");
		}
		return "include/alert";
	}
	
	@RequestMapping("/board/edit.do")
	public String edit(Model model, BoardVo vo) {
		//DB에서 가지고 와야지
		model.addAttribute("vo", service.edit(vo));
		return "board/edit";
	}
	
	@RequestMapping("/board/update.do")
	public String update(Model model, BoardVo vo, 
							@RequestParam("file") MultipartFile file, HttpServletRequest req) {
		//service.insert(vo, filename, req)
		if( !file.isEmpty()) { //첨부파일이 있으면
			try {
				String org = file.getOriginalFilename(); //원본파일명
			
				String ext = ""; //확장자명
				
				ext = org.substring(org.lastIndexOf("."));
				String real = new Date().getTime()+ext; // 서버에 저장할 실제 파일명
	//			System.out.println("org:"+org);
	//			System.out.println("real:"+real);
				
				//파일 저장
				String path = req.getRealPath("/upload/"); //경로
		//		System.out.println(path);
				file.transferTo(new File(path+real)); //경로 + 파일명 저장
				
				//vo에 set
				vo.setFilename_org(org);
				vo.setFilename_real(real);
				
			} catch (Exception e) {
			
			}
		}
		int r =service.update(vo);
		//r > 0 : 정상작동 -> alert -> 목록으로 이동
		//r ==0 : 비정상 -> alert -> 이전페이지로 이동
		
		if (r > 0) {
			model.addAttribute("msg", "정상적으로 등록되었습니다.");
			model.addAttribute("url", "index.do");
		} else {
			model.addAttribute("msg", "등록실패.");
			model.addAttribute("url", "edit.do?no="+vo.getNo());
		}
		return "include/alert";
	}
	
	
	
	
	
	@RequestMapping("/board/delete.do")
	public String delete(Model model, BoardVo vo, HttpServletRequest req) {
	
		//model.addAttribute("vo", service.delete(vo));
		int r =service.delete(vo);
		//r > 0 : 정상작동 -> alert -> 목록으로 이동
		//r ==0 : 비정상 -> alert -> 이전페이지로 이동
		
		
		if (r > 0) {
			model.addAttribute("result", "true");
		
		} else {
			model.addAttribute("result", "false");
		}
		return "include/result";
	}
	

}
