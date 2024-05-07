package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Platform;

public class PlatformDAO extends DatabaseDAO implements GenericDAO<Platform> {
    public PlatformDAO(Connection conn) {
        super(conn);
    }
    @Override
    public boolean insert(Platform platform) {
        String[] columns = {"name"};
        Object[] values = {platform.getName()};
        return insert("platforms", columns, values);
    }

    public List<Platform> getAll() {
        List<Platform> list = new ArrayList<Platform>();
        String[] columns = {"id", "name"};
        ResultSet rs = select("platforms", columns, null, null);
        try {
            while (rs.next()) {
                Platform pl = new Platform();
                pl.setId(rs.getInt("id"));
                pl.setName(rs.getString("name"));
                list.add(pl);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Platform getById(int id) {
        Platform platform = null;
        String[] columns = {"name"};
        Object[] values = {id};
        ResultSet rs = select("platforms", columns, "id=?", values);
        try {
            if (rs.next()) {
                platform = new Platform();
                platform.setId(id);
                platform.setName(rs.getString("name"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return platform;
    }

    public Platform getLastPlatform() {
        Platform platform = null;
        String[] columns = {"id", "name"};
        ResultSet rs = select("platforms", columns, null, null);
        try {
            if (rs.last()) {
                platform = new Platform();
                platform.setId(rs.getInt("id"));
                platform.setName(rs.getString("name"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return platform;
    }

    public boolean delete(int id) {
        String condition = "id=" + id;
        return delete("platforms", condition);
    }



	@Override
	public boolean update(Platform obj) {
		// TODO Auto-generated method stub
		return false;
	}

	
}

