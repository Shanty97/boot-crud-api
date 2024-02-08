package com.boot.crud.api.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.boot.crud.api.models.Student;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MiniStorage {

	private Map<Integer, Student> mapStore = new HashMap<Integer, Student>();

	public void create(Student data) {
		mapStore.put(data.getId(), data);
	}
	
	public List<Student> findAll() {
		return mapStore.values().stream().collect(Collectors.toList());
	}
	
	public Student findById(int id) {
		return mapStore.get(id);
	}
	
	public List<Student> findByAge(int age) {
		
		List<Student> tempStore = new ArrayList<Student>();
		Set<Integer> keys = mapStore.keySet();
		for(int key: keys) {
			if(mapStore.get(key).getAge() == age) {
				tempStore.add(mapStore.get(key));
			}
		}
		return tempStore;
	}
	
	
}
