package org.academiadecodigo.whiledlings.map;

public enum Direction {
    HORIZONTAL,
    VERTICAL;

   public static String[] getAll(){
       String[] directions = new String[values().length];
       for (int i = 0; i < values().length; i++) {
           directions[i] = values()[i].name();
       }
       return directions;
   }

}
