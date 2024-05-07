package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Genre;

public class GenreDAO extends DatabaseDAO implements GenericDAO<Genre> {

    public GenreDAO(Connection conn) {
        super(conn);
    }

    public boolean insert(Genre genre) {
        String[] columns = {"name"};
        Object[] values = {genre.getName()};
        return insert("genre", columns, values);
    }

    public List<Genre> getAll() {
        List<Genre> listGenre = new ArrayList<Genre>();

        try {
            String sql = "SELECT * FROM genre";
            ResultSet rs = select("genre", null, null, null);
            while (rs.next()) {
                Genre genre = new Genre();
                genre.setId(rs.getInt(1));
                genre.setName(rs.getString(2));
                listGenre.add(genre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listGenre;
    }

    public Genre getById(int id) {
        Genre genre = new Genre();
        try {
            String sql = "SELECT * FROM genre WHERE genre_id=?";
            Object[] values = {id};
            ResultSet rs = select("genre", null, sql, values);
            if (rs.next()) {
                genre.setId(rs.getInt(1));
                genre.setName(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genre;
    }

    public Genre getLastGenre() {
        Genre genre = null;
        try {
            String sql = "SELECT * FROM genre ORDER BY genre_id DESC LIMIT 1";
            ResultSet rs = select("genre", null, sql, null);
            if (rs.next()) {
                genre = new Genre();
                genre.setId(rs.getInt(1));
                genre.setName(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genre;
    }

    public boolean delete(int id) {
        String condition = "genre_id=?";
        Object[] values = {id};
        return delete("genre", condition, values);
    }

}

