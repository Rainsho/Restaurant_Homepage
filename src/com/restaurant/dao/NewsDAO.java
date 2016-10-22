package com.restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.restaurant.entity.News;

public class NewsDAO extends BaseDAO {

	public ArrayList<News> getAll() {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = getCon();
			String sql = "select * from news order by ndate desc";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			ArrayList<News> list = new ArrayList<News>();
			while (rs.next()) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(rs.getTimestamp(3));
				News obj = new News(rs.getInt(1), rs.getString(2), cal,
						rs.getString(4), rs.getString(5));
				list.add(obj);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, pst, rs);
		}
		return null;
	}

	public void AddNews(News obj) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = getCon();
			String sql = "insert into news (nauthor, ndate, ncontent, ntag) values (?, ?, ?, ?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, obj.getNauthor());
			pst.setString(2, obj.fmtDate());
			pst.setString(3, obj.getNcontent());
			pst.setString(4, obj.getNtag());
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, pst, null);
		}
	}

	public void DelNews(int nid) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = getCon();
			String sql = "delete from news where nid=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, nid);
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, pst, null);
		}
	}

	public void UpdateNews(News news) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = getCon();
			String sql = "update news set nauthor=?, ndate=?, ncontent=?, ntag=? where nid=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, news.getNauthor());
			pst.setString(2, news.fmtDate());
			pst.setString(3, news.getNcontent());
			pst.setString(4, news.getNtag());
			pst.setInt(5, news.getNid());
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, pst, null);
		}
	}

	public List<News> getPage(List<News> list_all, int page) {
		List<News> list_page = list_all.subList(page * 10 - 10,
				Math.min(page * 10, list_all.size()));
		return list_page;
	}

	public ArrayList<News> filterAuthor(String nauthor) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = getCon();
			String sql = "select * from news where nauthor like ?  order by ndate desc";
			pst = con.prepareStatement(sql);
			pst.setString(1, "%" + nauthor + "%");
			rs = pst.executeQuery();
			ArrayList<News> list = new ArrayList<News>();
			while (rs.next()) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(rs.getTimestamp(3));
				News obj = new News(rs.getInt(1), rs.getString(2), cal,
						rs.getString(4), rs.getString(5));
				list.add(obj);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, pst, rs);
		}
		return null;
	}

	public News getNewOne() {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = getCon();
			String sql = "select * from news order by ndate desc limit 1";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(rs.getTimestamp(3));
				return new News(rs.getInt(1), rs.getString(2), cal,
						rs.getString(4), rs.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, pst, rs);
		}
		return null;
	}

	public News getById(int nid) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = getCon();
			String sql = "select * from news where nid = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, nid);
			rs = pst.executeQuery();
			if (rs.next()) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(rs.getTimestamp(3));
				return new News(rs.getInt(1), rs.getString(2), cal,
						rs.getString(4), rs.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, pst, rs);
		}
		return null;
	}

	public ArrayList<News> getPage(int page) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = getCon();
			String sql = "select * from news order by ndate desc limit 10 offset ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, page * 10 - 10);
			rs = pst.executeQuery();
			ArrayList<News> list = new ArrayList<News>();
			while (rs.next()) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(rs.getTimestamp(3));
				News obj = new News(rs.getInt(1), rs.getString(2), cal,
						rs.getString(4), rs.getString(5));
				list.add(obj);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, pst, rs);
		}
		return null;
	}

}
