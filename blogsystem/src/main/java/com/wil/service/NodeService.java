package com.wil.service;

import com.wil.dao.NodeDao;
import com.wil.entity.Node;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wil on 2018/8/17.
 */
@Service
public class NodeService {

    NodeDao nodeDao = new NodeDao();

    public Node findNodeById(int id) {
       return nodeDao.findById(id);
    }

    public List<Node> findAll() {
        return nodeDao.findAll();
    }

}
