package edu.icet.pos.controller.item;

import edu.icet.pos.crudUtil.CrudUtil;
import edu.icet.pos.db.DBConnection;
import edu.icet.pos.model.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

@RequiredArgsConstructor
public class ItemController implements ItemService{
    private static ItemController instance;

    private final Connection connection;
    private final Statement statement;
    String letter="([A-Z])";

    private ItemController(){
        try {
            connection=DBConnection.getInstance().getConnection();
            statement= connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static ItemController getInstance(){
        if(instance==null){
           return instance=new ItemController();
        }
        return instance;
    }


    @Override
    public Item searchItem(String itemCode) {
        ResultSet resultSet = null;
        try {
            resultSet = resultSet = CrudUtil.execute("SELECT * FROM item WHERE item_code=?",itemCode);
            while(resultSet.next()) {
                return new Item(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public ObservableList<Item> getAllItems() {

        return null;
    }

    @Override
    public ObservableList getSelectedItems(String string) {
        String sql = "SELECT * FROM item WHERE REGEXP_LIKE(item_code,'^"+string+"');";
        ObservableList list = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                list.add(
                        new String(
                                resultSet.getString("item_code")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public boolean AddItem(Item item) {
        String sql = "INSERT INTO item VALUES(?,?,?,?,?)";
        try {
            CrudUtil.execute(
                    sql,
                    item.getItemCode(),
                    item.getItemCategory(),
                    item.getItemName(),
                    item.getDescription(),
                    item.getImgUrl()

            );
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public String generateItemCode(String category) {
        try {
            final String KIDS="KIDS Wear";
            final String MENS="MENS Wear";
            final String LADIES="LADIES Wear";
            final String TOY="TOYS";
            final String OTHER="OTHER";
            String itemCode;
            int count=0;
            ResultSet resultSet = null;

            switch (category){
                case KIDS : resultSet=statement.executeQuery(
                        "SELECT COUNT(*) FROM item WHERE item_code LIKE 'KID%'"
                );
                    break;
                case MENS : resultSet=statement.executeQuery(
                        "SELECT COUNT(*) FROM item WHERE item_code LIKE 'MEN%'"
                );
                    break;
                case LADIES : resultSet=statement.executeQuery(
                        "SELECT COUNT(*) FROM item WHERE item_code LIKE 'LAD%'"
                );
                    break;
                case TOY : resultSet=statement.executeQuery(
                        "SELECT COUNT(*) FROM item WHERE item_code LIKE 'TOY%'"
                );
                    break;
                case OTHER : resultSet=statement.executeQuery(
                        "SELECT COUNT(*) FROM item WHERE item_code LIKE 'OTH%'"
                );
                    break;
                default: break;
            }

            while(Objects.requireNonNull(resultSet).next()){
                count = resultSet.getInt(1);
            }

            if(count==0){
                switch (category){
                    case KIDS : return "KID000001";
                    case MENS : return "MEN000001";
                    case LADIES : return "LAD000001";
                    case TOY : return "TOY000001";
                    case OTHER : return "OTH000001";
                    default: return "";
                }
            }

            if(category.equals(KIDS)){
                itemCode = generateKidsItemsCode();


            }else if(category.equals(MENS)){
                itemCode = generateMensItemsCode();

            }else if(category.equals(LADIES)){
                itemCode = generateLadiesItemsCode();

            }else if(category.equals(TOY)){
                itemCode = generateToysItemsCode();

            }else{
                itemCode = generateOtherItemsCode();
            }

            return itemCode;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateKidsItemsCode(){
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(
                    "SELECT * FROM item WHERE item_code LIKE 'KID%' ORDER BY item_code DESC LIMIT 1;"
            );
            if(resultSet.next()){
                String string = resultSet.getString(1);
                String s = string.replaceAll(letter, "");
                int i = Integer.parseInt(s)+1;
                if (i < 10) {
                    return "KID00000" + i;
                } else if (i < 100) {
                    return "KID0000" + i;
                } else if (i < 1000) {
                    return "KID000" + i;
                } else if (i < 10000) {
                    return "KID00" + i;
                } else if (i < 100000) {
                    return "KID0" + i;
                } else {
                    return "KID"+i;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public String generateLadiesItemsCode(){
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(
                    "SELECT * FROM item WHERE item_code LIKE 'LAD%' ORDER BY item_code DESC LIMIT 1;"
            );
            if(resultSet.next()){
                String string = resultSet.getString(1);
                String s = string.replaceAll(letter, "");
                int i = Integer.parseInt(s)+1;
                if (i < 10) {
                    return "LAD00000" + i;
                } else if (i < 100) {
                    return "LAD0000" + i;
                } else if (i < 1000) {
                    return "LAD000" + i;
                } else if (i < 10000) {
                    return "LAD00" + i;
                } else if (i < 100000) {
                    return "LAD0" + i;
                } else {
                    return "LAD"+i;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }



    public String generateMensItemsCode(){

        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(
                    "SELECT * FROM item WHERE item_code LIKE 'MEN%' ORDER BY item_code DESC LIMIT 1;"
            );
            if(resultSet.next()){
                String string = resultSet.getString(1);
                String s = string.replaceAll(letter, "");
                int i = Integer.parseInt(s)+1;
                if (i < 10) {
                    return "MEN00000" + i;
                } else if (i < 100) {
                    return "MEN0000" + i;
                } else if (i < 1000) {
                    return "MEN000" + i;
                } else if (i < 10000) {
                    return "MEN00" + i;
                } else if (i < 100000) {
                    return "MEN0" + i;
                } else {
                    return "MEN"+i;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public String generateToysItemsCode(){
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(
                    "SELECT * FROM item WHERE item_code LIKE 'TOY%' ORDER BY item_code DESC LIMIT 1;"
            );
            if(resultSet.next()){
                String string = resultSet.getString(1);
                String s = string.replaceAll(letter, "");
                int i = Integer.parseInt(s)+1;
                if (i < 10) {
                    return "TOY00000" + i;
                } else if (i < 100) {
                    return "TOY0000" + i;
                } else if (i < 1000) {
                    return "TOY000" + i;
                } else if (i < 10000) {
                    return "TOY00" + i;
                } else if (i < 100000) {
                    return "TOY0" + i;
                } else {
                    return "TOY"+i;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public String generateOtherItemsCode(){
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(
                    "SELECT * FROM item WHERE item_code LIKE 'OTH%' ORDER BY item_code DESC LIMIT 1;"
            );
            if(resultSet.next()){
                String string = resultSet.getString(1);
                String s = string.replaceAll(letter, "");
                int i = Integer.parseInt(s)+1;
                if (i < 10) {
                    return "OTH00000" + i;
                } else if (i < 100) {
                    return "OTH0000" + i;
                } else if (i < 1000) {
                    return "OTH000" + i;
                } else if (i < 10000) {
                    return "OTH00" + i;
                } else if (i < 100000) {
                    return "OTH0" + i;
                } else {
                    return "OTH"+i;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

}
