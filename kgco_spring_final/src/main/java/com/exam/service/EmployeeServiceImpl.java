package com.exam.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.domain.EmployeeStatusVO;
import com.exam.domain.EmployeeVo;
import com.exam.domain.EmployeeWorkNoteVO;
import com.exam.domain.EstateCountVo;
import com.exam.domain.PebyDeptVO;
import com.exam.domain.PebyGateVO;
import com.exam.mapper.EmployeeMapper;

import lombok.Setter;

@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Setter(onMethod_ = @Autowired)
	private EmployeeMapper mapper;

	@Override
	public EmployeeVo getMemberById(String id) {
		return mapper.getMemberById(id);
	}

	@Override
	public int loginCheck(String id, String pass) {

		int check = -1;

		EmployeeVo employeeVO = mapper.getMemberById(id);

		if (employeeVO != null) {
			if (pass.equals(employeeVO.getEpassword())) {
				check = 1;

			} else {
				check = 0;
			}
		} else {
			check = -1;
		}

		return check;
	}

	@Override
	public int updateEstate(String eid, String estate) {
		return mapper.updateEstate(eid, estate);
	}

	@Override
	public List<PebyDeptVO> getPebyDept() {
		return mapper.getPebyDept();
	}

	@Override
	public List<PebyGateVO> getPebyGate() {
		return mapper.getPebyGate();
	}

	@Override
	public List<EmployeeVo> getAllEid() {
		return mapper.getAllEid();
	}

	@Override
	public int updateByPass(String eid, String epasswrod) {
		return mapper.updateByPass(eid, epasswrod);
	}

	@Override
	public List<EmployeeVo> getEmployeeList() {
		return mapper.getEmployeeList();
	}

	@Override
	public List<EmployeeStatusVO> getEmployeeStatus(String authName) {
		return mapper.getEmployeeStatus(authName);
	}

	  @Override
	    public JSONArray getCountEstate(String eid) {

	        JSONArray jArray = new JSONArray();
	        JSONArray colName = new JSONArray();
	        colName.add("직원근무현황");
	        colName.add("직원수");
	        jArray.add(colName);

	        List<EstateCountVo> estateList = mapper.getCountEstate(eid);
	        String estateName="";
	        

	       
	        System.out.println("estateList==============estateList" + estateList.toString());
	        for (EstateCountVo estateCountList : estateList) {
	            JSONArray rowArray = new JSONArray();
	            String items= estateCountList.getEstate();
	            if(items.equals("nor")) {
	                estateName="출근";
	            }else if(items.equals("abse")) {
	                estateName="결근";
	            }else if(items.equals("va")) {
	                estateName="휴가";
	            }else if(items.equals("late")) {
	                estateName="지각";
	            }
	            
	            rowArray.add(estateName);
	            rowArray.add(estateCountList.getNum());
	            System.out.println("=====estateName=====:"+estateName);

	            jArray.add(rowArray);
	        }

	        System.out.println("jArray=========" + jArray.toString());
	        return jArray;
	    }

	@Override
	public int updatePebyGate(String eIdTR, String eIdMA, int gateNum, String eGate) { // 임시 직원 근무일지 작성메소드
		// 현재시간
		Date date = new Date();
		date.getDate(); // 현재시간
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
		Long curTime = Long.parseLong(sdf.format(date).substring(0, 8)); // yyyy-mm
		Long time = Long.parseLong(sdf.format(date).substring(8, 12)); // hh-mm
		// 근무그룹판별
		String eGroup = "";
		if (200 <= time && time < 1000) {
			eGroup = "C";
		} else if (1000 <= time && time < 1800) {
			eGroup = "A";
		} else if (1800 <= time || time < 200) {
			eGroup = "B";
		}
		return mapper.updatePebyGate(eGroup, eIdTR, eIdMA, gateNum, eGate);
	}// updatePebyGate

	@Override
	public int insertByWorkNote(String date, String eidAndWorkNote) {
		return mapper.insertByWorkNote(date, eidAndWorkNote);

	}

	@Override
	public List<EmployeeWorkNoteVO> getWorkNote() {
		return mapper.getWorkNote();
	}

	@Override
	public int updateByStatus(String eid, String estate) {
		// TODO Auto-generated method stub
		return mapper.updateByStatus(eid, estate);
	}

	@Override
	public String getEmployeeStatusByEid(String eid) {
		return mapper.getEmployeeStatusByEid(eid);
	}

	@Override
	public int updateByWorkNote(String date, String eidAndWorkNote) {
		return mapper.updateByWorkNote(date, eidAndWorkNote);
	}

	@Override
	public int updatePeByDept() {
		return mapper.updatePeByDept();
	}

	@Override
	public List<EmployeeStatusVO> memberSelect(String selec, String search) {
		List<EmployeeStatusVO> list = mapper.memberSelect(selec, search);
		System.out.println("selec" + selec + ",search" + search);
		for (EmployeeStatusVO statusVO : list)
			System.out.println(statusVO);

		return list;
	}

	@Override
	public EmployeeStatusVO selfSchedule(String eid) {

		return mapper.selfSchedule(eid);
	}

	@Override
	public void userInfoUpdate(String eid, String ename, String ephone) {

		mapper.userInfoUpdate(eid, ename, ephone);
	}

	@Override
	public int getEmployeeLev(String eid) {
		return mapper.getEmployeeLev(eid);
	}

	@Override
	public void updateEmployeeStatus(String eid, String eGate) {
		// TODO Auto-generated method stub
		  mapper.updateEmployeeStatus(eid, eGate);
	}
	
	  @Override
	    public EmployeeVo modifyPwCheck(String eid, String epassword) {
	        System.out.println(eid+epassword);
	        return mapper.modifyPwCheck(eid, epassword);
	    }
    
}
