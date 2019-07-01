package com.onest.consoleApp.repositories.impl;

import com.onest.consoleApp.models.Tag;
import com.onest.consoleApp.repositories.TagRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TagRepositoryImpl implements TagRepository {

    private Connection connection;

    private Statement stmt = null;

    public TagRepositoryImpl() {

        try {
            this.connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/todos",
                    "postgres",
                    "558226");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Tag> findAllByTodoId(Long todoId) {

        ArrayList<Tag> temp = new ArrayList<Tag>();

        String sql = "select tag_name from tags, todos_to_tags " +
                "where todos_to_tags.tag_id = tags.id and todo_id = ?;";

        try {

            PreparedStatement stmt = connection.prepareStatement(sql);

//            String sql = "select tag_name from tags, todos_to_tags " +
//                    "where todos_to_tags.tag_id = tags.id and todo_id = '"+todoId+"';";


            stmt.setInt(1,todoId.intValue());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Tag todo = new Tag();
                //todo.setId(rs.getLong("id"));
                todo.setName(rs.getString("tag_name"));
                temp.add(todo);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return temp;

       // return Collections.emptyList();
    }

    @Override
    public void addTagByTodoId(long todoId, long tagId) {
        String sql = "insert into todos_to_tags(todo_id, tag_id) values (?, ?)";

        try {

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setLong(1, todoId);
            stmt.setLong(2, tagId);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public ArrayList<Tag> findAll(){

        String sql = "select * from tags;";

        ArrayList<Tag> temp = new ArrayList<Tag>();

        try {

            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){

                Tag tag = new Tag();
                tag.setId(rs.getLong("id"));
                tag.setName(rs.getString("tag_name"));

                temp.add(tag);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  temp;
    }
}
