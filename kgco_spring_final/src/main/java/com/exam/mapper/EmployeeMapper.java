package com.exam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.exam.domain.EmployeeStatusVO;
import com.exam.domain.EmployeeVo;
import com.exam.domain.EmployeeWorkNoteVO;
import com.exam.domain.EstateCountVo;
import com.exam.domain.PebyDeptVO;
import com.exam.domain.PebyGateVO;

public interface EmployeeMapper {

	public EmployeeVo getMemberById(String eid);

	public List<EmployeeVo> getEmployeeList();

	public String searchByEid(String eid); // 아이디 값으로 해당 직원근무상태가 작성되어있는지 확인

	public List<EmployeeVo> getAllEid();

//	public int countById(String id); // 카운트 id

	public EmployeeVo getUserInfo(String id); // id 값으로 유저정보가져오기

	public int updateEstate(@Param("eid") String eid, @Param("estate") String estate); // 직원근무상태 업데이트

	public int updatePeByDept();// 부서별 인원현황 업데이트

	public List<PebyDeptVO> getPebyDept();// 부서별 인원 현황

	public List<PebyGateVO> getPebyGate(); // 게이트별 인원 현황

	public List<EmployeeStatusVO> getEmployeeStatus(@Param("authName") String authName); // 직원 근무 현황

	public String getEmployeeStatusByEid(String eid); // 직원 근무 현황

	public List<EstateCountVo> getCountEstate(String eid);// 직원근무현황 카운트

	public int updateByPass(@Param("eid") String eid, @Param("epassword") String epasswrod); // 비밀번호 보안 업데이트

	public int updatePebyGate(@Param("egroup") String eGroup, @Param("eidtr") String eIdTR,
			@Param("eidma") String eIdMA, @Param("gateNum") int gateNum, @Param("eGate") String eGate); // 게이트별 근무인원 현황/

	public int insertByWorkNote(@Param("date") String date, @Param("eidAndWorkNote") String eidAndWorkNote); // 직원 근무노트
																												// 삽입

	public int updateByWorkNote(@Param("date") String date, @Param("eidAndWorkNote") String eidAndWorkNote);

	public List<EmployeeWorkNoteVO> getWorkNote(); // 직원근무일지 가져오기

	public int updateByStatus(@Param("eid") String eid, @Param("estate") String estate); // 직원 근무 일지 업데이트

	public List<EmployeeStatusVO> memberSelect(@Param("selec") String selec, @Param("search") String search);// 직원근무상태
																												// 검색

	public EmployeeStatusVO selfSchedule(String eid); // 개인 근무상태 확인

	public void userInfoUpdate(@Param("eid") String eid, @Param("ename") String ename, @Param("ephone") String ephone);// 개인정보수정
	
	public int getEmployeeLev(String eid);

	public void updateEmployeeStatus( @Param("eid")String eid,@Param("eGate") String eGate);
	
	public EmployeeVo modifyPwCheck(@Param("eid")String eid,@Param("epassword")String epassword);


}
