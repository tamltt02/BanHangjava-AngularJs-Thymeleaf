package com.example.assignmentjava6.restController;

import com.example.assignmentjava6.entity.Role;
import com.example.assignmentjava6.service.RoleService;
import com.example.assignmentjava6.service.UploadService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.File;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/roles")
public class RoleRestController {

    @Autowired
    RoleService roleService;

    @GetMapping()
    public List<Role> getAll() {

        return roleService.findAll();
    }
}
