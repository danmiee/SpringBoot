package edu.pnu.dao.member;

import java.util.List;

import edu.pnu.domain.MemberVO;

public interface MemberInterface {
	
	List<MemberVO> getMembers();

	MemberVO getMember(Integer id);

	MemberVO addMember(MemberVO vo);

	MemberVO updateMember(MemberVO vo);

	MemberVO removeMember(Integer id);
	
	String getSqlString();
}
