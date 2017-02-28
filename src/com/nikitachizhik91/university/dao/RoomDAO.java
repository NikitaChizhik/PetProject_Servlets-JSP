package com.nikitachizhik91.university.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nikitachizhik91.university.domain.Room;

public class RoomDAO implements Crud<Room> {

	public Room create(Room room) {
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement("insert into rooms (number) values(?)");) {

			statement.setString(1, room.getNumber());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.getMessage();
		}
		return room;
	}

	public Room getById(int id) {
		Room roomRecieved = new Room();

		try (Connection connection = Connector.getConnection();

				PreparedStatement statement = connection.prepareStatement("select * from rooms where room_id=?")) {

			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {

					roomRecieved.setId(resultSet.getInt("room_id"));
					roomRecieved.setNumber(resultSet.getString("number"));
				}
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return roomRecieved;
	}

	public List<Room> getAll() {
		List<Room> roomsRecieved = new ArrayList<Room>();

		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement("select * from rooms order by number");
				ResultSet resultSet = statement.executeQuery();) {

			while (resultSet.next()) {
				Room room = new Room();
				room.setId(resultSet.getInt("room_id"));
				room.setNumber(resultSet.getString("number"));
				roomsRecieved.add(room);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return roomsRecieved;
	}

	public Room update(int id, Room room) {
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("update rooms set number=? where room_id =?");) {

			statement.setString(1, room.getNumber());
			statement.setInt(2, id);

			statement.executeUpdate();

		} catch (SQLException e) {
			e.getMessage();
		}
		return room;
	}

	public void delete(int id) {
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement("delete from rooms where id =?");) {

			statement.setInt(1, id);

			statement.executeUpdate();

		} catch (SQLException e) {
			e.getMessage();
		}
	}

}