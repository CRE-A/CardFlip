package project.DAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.DTO.SelectedCardsDto;

import java.util.List;


@Repository
public class SelectedCardsDaoImpl implements SelectedCardsDao {
    @Autowired
    private SqlSession session;
    private static String namespace = "project.DAO.SelectedCardsMapper.";


    @Override
    public List<SelectedCardsDto> select(String id) throws Exception{
        System.out.println("selectedCardsMapper // id = " + id);
        return session.selectList(namespace + "select", id);
    }

    @Override
    public int insert(SelectedCardsDto selectedCardsDto) throws Exception{
        return session.insert(namespace + "insert", selectedCardsDto);  }

    @Override
    public int delete(String id) throws Exception{
        return session.delete(namespace + "delete", id);
    }

    @Override
    public int deleteAll(String id) throws Exception{
        System.out.println("dao까지 옴 //  id = " + id);
        return session.delete(namespace + "deleteAll",id);
    }

    @Override
    public int count(String id) throws Exception{
        return session.selectOne(namespace + "count", id);
    }

    }