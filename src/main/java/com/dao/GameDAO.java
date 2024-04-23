package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Game;

public class GameDAO {
	Connection conn = null;
	PreparedStatement pst;
	ResultSet rs;

	public GameDAO(Connection conn) {
		this.conn = conn;
	}

	public boolean insertGame(Game game) {

		boolean f = false;

		try {
			String sql = "insert into games(title,desription,price,platform_id,image_url,genre_id,year,developer_name) values(?,?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, game.getTitle());
			pst.setString(2, game.getDescription());
			pst.setDouble(3, game.getPrice());
			pst.setInt(4, game.getPlatform_id());
			pst.setString(5, game.getPhotoName());
			pst.setInt(6, game.getGenre_id());
			pst.setString(7, game.getYear());
			pst.setString(8, game.getDeveloper());

			int i = pst.executeUpdate();

			if (i == 1) {
				f = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

	public List<Game> getAllGames() {
		List<Game> listOfGames = new ArrayList<Game>();

		String sql = "select * from games order by game_id DESC";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				Game game = new Game();
				game.setId(rs.getInt(1));
				game.setTitle(rs.getString(2));
				game.setDescription(rs.getString(3));
				game.setPrice(rs.getDouble(4));
				game.setPlatform_id(rs.getInt(5));
				game.setPhotoName(rs.getString(6));
				game.setGenre_id(rs.getInt(7));
				game.setYear(rs.getString(8));

				listOfGames.add(game);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listOfGames;
	}

	public List<Game> getAllRecentGames() {
		List<Game> listOfGames = new ArrayList<Game>();

		String sql = "select * from games order by game_id DESC";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			int i = 1;
			while (rs.next() && i <= 4) {
				Game game = new Game();
				game.setId(rs.getInt(1));
				game.setTitle(rs.getString(2));
				game.setDescription(rs.getString(3));
				game.setPrice(rs.getDouble(4));
				game.setPlatform_id(rs.getInt(5));
				game.setPhotoName(rs.getString(6));
				game.setGenre_id(rs.getInt(7));
				game.setYear(rs.getString(8));
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
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			int i = 1;
			while (rs.next() && i <= 4) {
				Game game = new Game();
				game.setId(rs.getInt(1));
				game.setTitle(rs.getString(2));
				game.setDescription(rs.getString(3));
				game.setPrice(rs.getDouble(4));
				game.setPlatform_id(rs.getInt(5));
				game.setPhotoName(rs.getString(6));
				game.setGenre_id(rs.getInt(7));
				game.setYear(rs.getString(8));

				listOfGames.add(game);
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listOfGames;
	}

	public List<Game> getGameByGenreId(int genreId) throws SQLException {

		List<Game> listOfGames = new ArrayList<Game>();

		String sql = "select * from games where genre_id=?";
		pst = conn.prepareStatement(sql);
		pst.setInt(1, genreId);
		int i = 1;
		rs = pst.executeQuery();

		while (rs.next() && i <= 4) {
			Game game = new Game();
			game.setId(rs.getInt(1));
			game.setTitle(rs.getString(2));
			game.setDescription(rs.getString(3));
			game.setPrice(rs.getDouble(4));
			game.setPlatform_id(rs.getInt(5));
			game.setPhotoName(rs.getString(6));
			game.setGenre_id(rs.getInt(7));
			game.setYear(rs.getString(8));

			listOfGames.add(game);
			i++;
		}

		return listOfGames;

	}

	public List<Game> getGamesBySearch(String keyword, int category_id, int platform_id, int minPrice, int maxPrice) {
		List<Game> listGames = new ArrayList<Game>();

		try {
			String sql = "SELECT * FROM games WHERE genre_id = ? AND platform_id = ? AND (title LIKE ? OR desription LIKE ? OR price LIKE ?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, category_id);
			pst.setInt(2, platform_id);
			pst.setString(3, "%" + keyword + "%");
			pst.setString(4, "%" + keyword + "%");
			pst.setString(5, "%" + keyword + "%");
			rs = pst.executeQuery();
			while (rs.next()) {
				Game game = new Game();
				game.setId(rs.getInt(1));
				game.setTitle(rs.getString(2));
				game.setDescription(rs.getString(3));
				game.setPrice(rs.getDouble(4));
				game.setPlatform_id(rs.getInt(5));
				game.setPhotoName(rs.getString(6));
				game.setGenre_id(rs.getInt(7));
				game.setYear(rs.getString(8));
				listGames.add(game);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listGames;

	}

	public List<Game> getGamesBySearchCat(String keyword, int category_id, int minPrice, int maxPrice) {
		List<Game> listGames = new ArrayList<Game>();

		try {
			String sql = "SELECT * FROM games WHERE genre_id = ? AND (title LIKE ? OR desription LIKE ? OR price LIKE ?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, category_id);
			pst.setString(2, "%" + keyword + "%");
			pst.setString(3, "%" + keyword + "%");
			pst.setString(4, "%" + keyword + "%");
			rs = pst.executeQuery();
			while (rs.next()) {
				double price=rs.getDouble(4);
				if(minPrice<=price && maxPrice>=price) {
				Game game = new Game();
				game.setId(rs.getInt(1));
				game.setTitle(rs.getString(2));
				game.setDescription(rs.getString(3));
				game.setPrice(rs.getDouble(4));
				game.setPlatform_id(rs.getInt(5));
				game.setPhotoName(rs.getString(6));
				game.setGenre_id(rs.getInt(7));
				game.setYear(rs.getString(8));
				listGames.add(game);
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listGames;

	}

	public List<Game> getGamesBySearchPlat(String keyword, int platform_id, int minPrice, int maxPrice) {
		List<Game> listGames = new ArrayList<Game>();

		try {
			String sql = "SELECT * FROM games WHERE platform_id = ? AND (title LIKE ? OR desription LIKE ? OR price LIKE ?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, platform_id);
			pst.setString(2, "%" + keyword + "%");
			pst.setString(3, "%" + keyword + "%");
			pst.setString(4, "%" + keyword + "%");
			rs = pst.executeQuery();
			while (rs.next()) {
				double price = rs.getDouble(4);
				if (price >= minPrice && price <= maxPrice) {
					Game game = new Game();
					game.setId(rs.getInt(1));
					game.setTitle(rs.getString(2));
					game.setDescription(rs.getString(3));
					game.setPrice(rs.getDouble(4));
					game.setPlatform_id(rs.getInt(5));
					game.setPhotoName(rs.getString(6));
					game.setGenre_id(rs.getInt(7));
					game.setYear(rs.getString(8));
					listGames.add(game);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listGames;
	}

	public List<Game> getGamesBySearch(String keyword, int minPrice, int maxPrice) {
		List<Game> listGames = new ArrayList<Game>();

		try {
			String sql = "SELECT * FROM games WHERE title LIKE ? OR desription LIKE ? OR price LIKE ?";
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setString(1, "%" + keyword + "%");
			pst.setString(2, "%" + keyword + "%");
			pst.setString(3, "%" + keyword + "%");
			rs = pst.executeQuery();
			while (rs.next()) {
				double price = rs.getDouble(4);
				if (price >= minPrice && maxPrice >= price) {
					Game game = new Game();
					game.setId(rs.getInt(1));
					game.setTitle(rs.getString(2));
					game.setDescription(rs.getString(3));
					game.setPrice(price);
					game.setPlatform_id(rs.getInt(5));
					game.setPhotoName(rs.getString(6));
					game.setGenre_id(rs.getInt(7));
					game.setYear(rs.getString(8));
					listGames.add(game);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listGames;

	}

	public List<Game> getGamesBySearch(int category_id, int platform_id, int minPrice, int maxPrice) {
		List<Game> listGames = new ArrayList<Game>();

		try {
			String sql = "SELECT * FROM games WHERE genre_id = ? AND platform_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, category_id);
			pst.setInt(2, platform_id);

			rs = pst.executeQuery();
			while (rs.next()) {
				double price = rs.getDouble(4);
				if (minPrice <= price && price <= maxPrice) {
					Game game = new Game();
					game.setId(rs.getInt(1));
					game.setTitle(rs.getString(2));
					game.setDescription(rs.getString(3));
					game.setPrice(price);
					game.setPlatform_id(rs.getInt(5));
					game.setPhotoName(rs.getString(6));
					game.setGenre_id(rs.getInt(7));
					game.setYear(rs.getString(8));
					listGames.add(game);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listGames;

	}

	public List<Game> getGamesByGenreAndPlatform(int category_id, int platform_id) {
		List<Game> listGames = new ArrayList<Game>();

		try {
			String sql = "SELECT * FROM games WHERE genre_id = ? AND platform_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, category_id);
			pst.setInt(2, platform_id);

			rs = pst.executeQuery();
			while (rs.next()) {
				Game game = new Game();
				game.setId(rs.getInt(1));
				game.setTitle(rs.getString(2));
				game.setDescription(rs.getString(3));
				game.setPrice(rs.getDouble(4));
				game.setPlatform_id(rs.getInt(5));
				game.setPhotoName(rs.getString(6));
				game.setGenre_id(rs.getInt(7));
				game.setYear(rs.getString(8));
				listGames.add(game);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listGames;
	}

	public List<Game> getGamesByDeveloper(int category_id, int platform_id, String developer) {
		List<Game> listGames = new ArrayList<Game>();

		try {
			String sql = "SELECT * FROM games WHERE genre_id = ? AND platform_id = ? AND developer_name=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, category_id);
			pst.setInt(2, platform_id);
			pst.setString(3, developer);

			rs = pst.executeQuery();
			while (rs.next()) {
				Game game = new Game();
				game.setId(rs.getInt(1));
				game.setTitle(rs.getString(2));
				game.setDescription(rs.getString(3));
				game.setPrice(rs.getDouble(4));
				game.setPlatform_id(rs.getInt(5));
				game.setPhotoName(rs.getString(6));
				game.setGenre_id(rs.getInt(7));
				game.setYear(rs.getString(8));
				listGames.add(game);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listGames;

	}

	public List<Game> getGamesBySearch(String keyword) {
		List<Game> listGames = new ArrayList<Game>();

		try {
			String sql = "SELECT * FROM games WHERE title LIKE ? OR desription LIKE ? OR price LIKE ?";
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setString(1, "%" + keyword + "%");
			pst.setString(2, "%" + keyword + "%");
			pst.setString(3, "%" + keyword + "%");
			rs = pst.executeQuery();
			while (rs.next()) {
				Game game = new Game();
				game.setId(rs.getInt(1));
				game.setTitle(rs.getString(2));
				game.setDescription(rs.getString(3));
				game.setPrice(rs.getDouble(4));
				game.setPlatform_id(rs.getInt(5));
				game.setPhotoName(rs.getString(6));
				game.setGenre_id(rs.getInt(7));
				game.setYear(rs.getString(8));
				listGames.add(game);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listGames;

	}

	public Game getGameById(int id) {
		Game game = null;
		try {
			String sql = "SELECT * FROM games WHERE game_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);

			rs = pst.executeQuery();
			while (rs.next()) {
				game = new Game();
				game.setId(rs.getInt(1));
				game.setTitle(rs.getString(2));
				game.setDescription(rs.getString(3));
				game.setPrice(rs.getDouble(4));
				game.setPlatform_id(rs.getInt(5));
				game.setPhotoName(rs.getString(6));
				game.setGenre_id(rs.getInt(7));
				game.setYear(rs.getString(8));
				game.setDeveloper(rs.getString(9));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return game;
	}
	
	public void closeConnection() {
		try {
			this.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
