package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Permission;
import com.example.demo.repository.PermissionRepository;

@Service
public class PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;
	
	public List<Permission> getAllPermissions(){
		List<Permission> permissions = permissionRepository.findAll();
		
		return permissions;
	}
}
