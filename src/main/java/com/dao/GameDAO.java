package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Game;

public class GameDAO extends DatabaseDAO implements GenericDAO<Game>{

    public GameDAO(Connection conn) {
       super(conn);
    }
@Override
    public boolean insert(Game game) {
        String tableName = "games";
        String[] columns = {"title", "description", "price", "platform_id", "image_url", "genre_id", "year", "developer_name"};
        Object[] values = {game.getTitle(), game.getDescription(), game.getPrice(), game.getPlatform_id(), game.getPhotoName(), game.getGenre_id(), game.getYear(), game.getDeveloper()};
        return insert(tableName, columns, values);
    }
@Override

    public List<Game> getAll() {
        List<Game> listOfGames = new ArrayList<>();
        String tableName = "games";
        String[] columns = {"game_id", "title", "description", "price", "platform_id", "image_url", "genre_id", "year"};
        ResultSet rs = select(tableName, columns, null,null);
        try {
            while (rs.next()) {
                Game game = mapResultSetToGame(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfGames;
    }
    private Game mapResultSetToGame(ResultSet rs) throws SQLException {
        Game game = new Game();
        game.setId(rs.getInt(1));
        game.setTitle(rs.getString(2));
        game.setDescription(rs.getString(3));
        game.setPrice(rs.getDouble(4));
        game.setPlatform_id(rs.getInt(5));
        game.setPhotoName(rs.getString(6));
        game.setGenre_id(rs.getInt(7));
        game.setYear(rs.getString(8));
        return game;
    }
	public List<Game> getAllRecentGames() {
		List<Game> listOfGames = new ArrayList<Game>();

		String sql = "select * from games order by game_id DESC";
		try {
			ResultSet rs=executeQuery(sql,null);
			int i = 1;
			while (rs.next() && i <= 4) {
				Game game =mapResultSetToGame(rs);
				listOfGames.add(game);
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listOfGames;
	}

	public List<Game> getAllNewGames() {
		List<Game> listOfGames = new ArrayList<Game>();

		String sql = "select * from games order by year DESC";
		try {
			ResultSet rs= executeQuery(sql,null);
			int i = 1;
			while (rs.next() && i <= 4) {
				Game game = mapResultSetToGame(rs);

				listOfGames.add(game);
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listOfGames;
	}

    public List<Game> getGameByGenreId(int genreId) {
        List<Game> listOfGames = new ArrayList<>();
        try {
            String[] columns = {"*"};
            String condition = "genre_id = ?";
            Object[] values = {genreId};

            ResultSet rs = select("games", columns, condition, values);

            int i = 1;
            while (rs.next() && i <= 4) {
	            Game game = mapResultSetToGame(rs);
          
                listOfGames.add(game);
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfGames;
    }

    public List<Game> getGamesBySearch(String keyword, int category_id, int platform_id, int minPrice, int maxPrice) {
        List<Game> listGames = new ArrayList<Game>();

        try {
            String[] columns = {"*"};
            String condition = "genre_id = ? AND platform_id = ? AND (title LIKE ? OR description LIKE ? OR price BETWEEN ? AND ?)";
            Object[] values = {category_id, platform_id, "%" + keyword + "%", "%" + keyword + "%", minPrice, maxPrice};

            ResultSet rs = select("games", columns, condition, values);

            while (rs.next()) {
                Game game =mapResultSetToGame(rs);
                listGames.add(game);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listGames;
    }
    public List<Game> getGamesBySearchCat(String keyword, int category_id, int minPrice, int maxPrice) {
        List<Game> listGames = new ArrayList<Game>();

        try {
            String sql = "SELECT * FROM games WHERE genre_id = ? AND (title LIKE ? OR description LIKE ? OR price BETWEEN ? AND ?)";
            Object[] params = {category_id, "%" + keyword + "%", "%" + keyword + "%", minPrice, maxPrice};
            ResultSet rs = executeQuery(sql, params);

            while (rs.next()) {
                double price = rs.getDouble(4);
                if (price >= minPrice && price <= maxPrice) {
                    Game game =mapResultSetToGame(rs);
                    listGames.add(game);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listGames;
    }


    public List<Game> getGamesBySearchPlat(String keyword, int platform_id, int minPrice, int maxPrice) {
        List<Game> listGames = new ArrayList<Game>();

        try {
            String sql = "SELECT * FROM games WHERE platform_id = ? AND (title LIKE ? OR description LIKE ? OR price BETWEEN ? AND ?)";
            Object[] params = {platform_id, "%" + keyword + "%", "%" + keyword + "%", minPrice, maxPrice};
            ResultSet rs = executeQuery(sql, params);

            while (rs.next()) {
                double price = rs.getDouble(4);
                if (price >= minPrice && price <= maxPrice) {
                    Game game =mapResultSetToGame(rs);
                    listGames.add(game);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listGames;
    }
    public List<Game> getGamesBySearch(String keyword, int minPrice, int maxPrice) {
        List<Game> listGames = new ArrayList<Game>();

        try {
            String sql = "SELECT * FROM games WHERE title LIKE ? OR description LIKE ? OR price BETWEEN ? AND ?";
            Object[] params = {"%" + keyword + "%", "%" + keyword + "%", minPrice, maxPrice};
            ResultSet rs = executeQuery(sql, params);

            while (rs.next()) {
                double price = rs.getDouble(4);
                if (price >= minPrice && price <= maxPrice) {
                    Game game =mapResultSetToGame(rs);
                    listGames.add(game);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listGames;
    }


    public List<Game> getGamesBySearch(int category_id, int platform_id, int minPrice, int maxPrice) {
        List<Game> listGames = new ArrayList<Game>();

        try {
            String sql = "SELECT * FROM games WHERE genre_id = ? AND platform_id = ?";
            Object[] params = {category_id, platform_id};
            ResultSet rs = executeQuery(sql, params);

            while (rs.next()) {
                double price = rs.getDouble(4);
                if (minPrice <= price && price <= maxPrice) {
                    Game game =mapResultSetToGame(rs);
                    listGames.add(game);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listGames;
    }


    public List<Game> getGamesByGenreAndPlatform(int category_id, int platform_id) {
        List<Game> listGames = new ArrayList<Game>();

        try {
            String[] columns = {"*"};
            String condition = "genre_id = ? AND platform_id = ?";
            Object[] values = {category_id, platform_id};

            ResultSet rs = select("games", columns, condition, values);

            while (rs.next()) {
                Game game = mapResultSetToGame(rs);
                listGames.add(game);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listGames;
    }


    public List<Game> getGamesByDeveloper(int category_id, int platform_id, String developer) {
        List<Game> listGames = new ArrayList<>();

        try {
            String[] columns = {"*"};
            String condition = "genre_id = ? AND platform_id = ? AND developer_name = ?";
            Object[] values = {category_id, platform_id, developer};

            ResultSet rs = select("games", columns, condition, values);

            while (rs.next()) {
                Game game = mapResultSetToGame(rs);
                listGames.add(game);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listGames;
    }


    public List<Game> getGamesBySearch(String keyword) {
        List<Game> listGames = new ArrayList<>();

        try {
            String[] columns = {"*"};
            String condition = "title LIKE ? OR desription LIKE ? OR price LIKE ?";
            Object[] values = {"%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%"};

            ResultSet rs = select("games", columns, condition, values);

            while (rs.next()) {
                Game game = mapResultSetToGame(rs);
                listGames.add(game);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listGames;
    }

	@Override

	public Game getById(int id) {
	    Game game = null;
	    try {
	        String[] columns = {"*"};
	        String condition = "game_id = ?";
	        Object[] values = {id};

	        ResultSet rs = select("games", columns, condition, values);

	        while (rs.next()) {
	            game = mapResultSetToGame(rs);

	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return game;
	}
	public Game getLastGame() {
	    Game game = null;
	    try {
	        String sql = "SELECT * FROM games ORDER BY game_id DESC LIMIT 1";
	        ResultSet rs = executeQuery(sql, null);

	        if (rs.next()) {
	            game = mapResultSetToGame(rs);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return game;
	}

	@Override
	public boolean update(Game obj) {
		return false;
	}
	@Override
	public boolean delete(int id) {
		return false;
	}

}
