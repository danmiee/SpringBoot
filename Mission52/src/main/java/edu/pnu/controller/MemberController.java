package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;

@RestController
public class MemberController {

	@Autowired
	MemberService ms;
	
	// 필드 사용 생성자
	public MemberController(MemberService ms) {
		System.out.printf("Called MemberController\n");
		this.ms = ms;
	}
	
	@GetMapping("/member")
	public List<MemberVO> getMembers() {
		System.out.println("getMembers() Success\n");
		return ms.getMembers();
	}
	
	@GetMapping("/member/{id}")
	public MemberVO getMember(@PathVariable int id) {
		System.out.printf("getMember(%d) Success\n", id);
		return ms.getMember(id);
	}
	
	@PostMapping("/member")
	public MemberVO addMember(MemberVO vo) {
		System.out.printf("addMember() Success\n");
		return ms.addMember(vo);
	}
	
	@PutMapping("/member")
	public MemberVO updateMember(MemberVO vo) {
		System.out.printf("updateMember(%d) Success\n", vo.getId());
		return ms.updateMember(vo);
	}
	
	@DeleteMapping("/member/{id}")
	public MemberVO removeMember(@PathVariable int id) {
		System.out.printf("removeMember(%d) Success\n", id);
		return ms.removeMember(id);
	}
	
}
