package com.example.demo.service;

import com.example.demo.dao.StudentJdbctemplateDAO;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author owner
 *
 */
@Service
@Slf4j
public class LookupStateCode {

	private ConcurrentHashMap<String, String> statecodecache; //for thread-safe
	private static volatile Object instance = null; //must be volatile

	@Autowired
	private StudentJdbctemplateDAO studentJdbctemplatedao;

	/**
	 * no instantiate
	 */
	private LookupStateCode() {
	}

	/**
	 * To fix the LI_LAZY_INIT_STATIC spotBug error.
	 * i.e. race condition in multi-thread lazy instantiation
	 * use double check lock here<br>
	 * to eliminate  DC_DOUBLECHECK bug, must use volatile local variable
	 */
	public ConcurrentHashMap<String, String> getInstance() {

		if (instance == null) {
			synchronized (LookupStateCode.class) {
				if (instance == null) {
					instance = new Object(); // so, it is not null
					statecodecache = new ConcurrentHashMap<String, String>();
					long count = studentJdbctemplatedao.getStateCode()
							.stream()
							.filter(e -> e.getStatus().equals("true"))
							.map(mp -> {
								statecodecache.put(
										mp.getName().trim(),
										mp.getStatus().trim()
										);
								return mp.getName();
							}).count();

					log.info("Total init State Code: " + count);
				}
			}
		}

		//statecodecache.forEach((k, v) -> log.info("Key: '" + k + "' Value: '" + v + "'"));

		return statecodecache;
	}

	public boolean check(String stcode) {
		log.info("total # of statecode for searching: " + getInstance().size());
		statecodecache.forEach((k, v) -> log.info("checking Key : '" + k + "' Value : '" + v + "'"));
		return statecodecache.containsKey(stcode);
	}
}
