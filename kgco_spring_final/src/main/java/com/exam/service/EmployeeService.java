package com.exam.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.json.simple.JSONArray;

import com.exam.domain.EmployeeStatusVO;
import com.exam.domain.EmployeeVo;
import com.exam.domain.EmployeeWorkNoteVO;
import com.exam.domain.PebyDeptVO;
import com.exam.domain.PebyGateVO;

public interface EmployeeService {

	public List<EmployeeVo> getEmployeeList();

	public int loginCheck(String id, String pass);

	public EmployeeVo getMemberById(String id);

	public List<EmployeeVo> getAllEid();

	public int updateEstate(String eid, String estate);

	public List<PebyDeptVO> getPebyDept();

	public List<PebyGateVO> getPebyGate();

	public List<EmployeeStatusVO> getEmployeeStatus(String authName);

	public String getEmployeeStatusByEid(String eid);

	public int updateByPass(String eid, String epasswrod);

	public JSONArray getCountEstate(String eid);

	public int updatePebyGate(String eIdTR, String eIdMA, int gateNum, String eGate);

	public int insertByWorkNote(String date, String eidAndWorkNote);

	public int updateByWorkNote(String date, String eidAndWorkNote);

	public List<EmployeeWorkNoteVO> getWorkNote();

	public int updatePeByDept();

	public int updateByStatus(String eid, String estate); // 직원 근무 일지 업데이트

	public List<EmployeeStatusVO> memberSelect(@Param("selec") String selec, @Param("search") String search);

	public EmployeeStatusVO selfSchedule(String eid);

	public void userInfoUpdate(@Param("eid") String eid, @Param("ename") String ename, @Param("ephone") String ephone);
	
	public int getEmployeeLev(String eid);

	public void updateEmployeeStatus(@Param("eid") String eid, @Param("eGate") String eGate);
	
	public EmployeeVo modifyPwCheck(@Param("eid")String eid,@Param("epassword")String epassword);
}
