package edu.pnu.service;

import java.util.List;

import edu.pnu.dao.MemberDAO;
import edu.pnu.dao.MemberDaoFileImpl;
import edu.pnu.dao.MemberDaoH2Impl;
import edu.pnu.dao.MemberDaoListImpl;
import edu.pnu.domain.MemberVO;

@SuppressWarnings("unused")
public class MemberService {

//	MemberDAO dao = new MemberDaoH2Impl();
//	MemberDAO dao = new MemberDaoListImpl();
	MemberDAO dao = new MemberDaoFileImpl();
	
	// 모든 멤버 정보를 JSON 형태로 브라우저에 출력
	public List<MemberVO> getMembers() {
		return dao.getMembers();
	}

	// 아이디가 {id} 인 member를 찾아서 브라우저에 출력
	public MemberVO getMember(String id) {
		return dao.getMember(id);
	}
	
	// 추가하고자 하는 member 정보를 전달, 추가된 객체를 출력
	public MemberVO addMember(MemberVO vo) {
		return dao.addMember(vo);
	}

	// 수정 대상 객체 정보를 전달, 수정된 객체를 출력
	public MemberVO updateMember(MemberVO vo) {
		return dao.updateMember(vo);
	}

	// 아이디가 {id} 인 member를 찾아서 삭제, 브라우저에는 삭제된 객체를 출력
	public MemberVO removeMember(String id) {
		return dao.removeMember(id);
	}
}
