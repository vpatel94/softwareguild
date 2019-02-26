/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingmachine.dao;
import vendingmachine.dto.*;
import java.util.*;
import java.math.BigDecimal;
import java.io.*;

/**
 *
 * @author vpatel
 */
public class VendingMachineDAOFileImpl implements VendingMachineDAO
{
    private List<Item> items = new ArrayList<>();
    private Change change;
    public static final String INV_FILE = "items.txt";
    public static final String DELIMITER = "::";
    
    
    @Override
    public List<Item> getItems()
    {
        return this.items;
    }
    
    @Override
    public void startMachine() throws VendingMachineDAOException
    {
        loadItems();
    }
    
    @Override
    public void vendItem(Item item, BigDecimal money) throws VendingMachineDAOException
    {
        int currentInv = item.getInventory();
        currentInv--;
        item.setInventory(currentInv);
        writeItems();
        change = new Change(money.subtract(item.getCost()));
    }
    
    @Override
    public BigDecimal showChange()
    {
        return change.getChange();
    }
    
    @Override
    public int[] calcChange()
    {
        int[] c = new int[4];
        c[0] = this.change.getQuarters();
        c[1] = this.change.getDimes();
        c[2] = this.change.getNickels();
        c[3] = this.change.getPennies();
        return c;
    }    
    
    private void loadItems() throws VendingMachineDAOException 
    {
        List<Item> itemsLoaded = new ArrayList<>();
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(INV_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachineDAOException("Could not load inventory data into memory.", e);
        }
        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) 
        {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Item currentItem = new Item();
            currentItem.setName(currentTokens[0]);
            currentItem.setCost(new BigDecimal(currentTokens[1]).setScale(2));
            currentItem.setInventory(Integer.parseInt(currentTokens[2]));
            itemsLoaded.add(currentItem);
        }
        scanner.close();
        items = itemsLoaded;
    }

    private void writeItems() throws VendingMachineDAOException 
    {
        PrintWriter write;
        try {
            write = new PrintWriter(new FileWriter(INV_FILE));
        } catch (IOException e) {
            throw new VendingMachineDAOException("Could not save inventory data.", e);
        }

        for (Item i : items) 
        {
            write.println(i.getName() + DELIMITER
                     + i.getCost() + DELIMITER 
                     + i.getInventory());
            write.flush();
        }
        write.close();
    }
}
