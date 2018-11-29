package com.oracle.tankwar.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.ByteOrder;
import java.util.ArrayList;

public class Game  extends JFrame{
    private ArrayList<JLabel> points=new ArrayList<>();
    private JMenuBar menuBar;
    private JMenu  game;
    private JMenuItem  start,pause,reStart,saveScore,exit;
    private JPanel  gameArea,toolbar;
    private JLabel  status;
    private TankThread  t;
    private JLabel tank;
    private int nowDirection=39;//记录当前坦克的运动方向 （默认坦克方向是向右）
    private int tankX=0,tankY=0;//记录坦克当前所处的位置
    private int moveSpeed=2;
    private ArrayList<JLabel> zhuantou=new ArrayList<>();//存储砖墙的集合
    private ArrayList<JLabel> gangtie=new ArrayList<>();//存储钢铁的集合
    private ArrayList<JLabel> caodi=new ArrayList<>();//存储草地的集合
    public Game(){
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setTitle("Tank war");
        this.setIconImage(Toolkit.getDefaultToolkit().createImage("sources/images/logo.gif"));
        //this.setLayout(new GridLayout(20,20,0,0));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initMenus();
        initComponents();
        this.paintComponents(getGraphics());
        this.paintAll(getGraphics());
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                moveTank(e.getKeyCode());

            }

            @Override
            public void keyReleased(KeyEvent e) {
                tank.setIcon(getTankIcon(nowDirection,false));
            }
        });
    }

    /**
     * 初始化游戏菜单
     */
    public void initMenus(){
        game=new JMenu("游戏");
        start=new JMenuItem("开始");
        pause=new JMenuItem("暂停");
        reStart=new JMenuItem("重新开始");
        saveScore=new JMenuItem("保存成绩");
        exit=new JMenuItem("退出");
        game.add(start);
        game.addSeparator();
        game.add(pause);
        game.addSeparator();
        game.add(reStart);
        game.addSeparator();
        game.add(saveScore);
        game.addSeparator();
        game.add(exit);
        game.addSeparator();
        menuBar=new JMenuBar();
        menuBar.add(game);
        this.setJMenuBar(menuBar);
    }

    /**
     * 初始化窗口的组件
     */
    public void initComponents(){
        toolbar=new JPanel();
        toolbar.setLayout(new GridLayout(1,10));
        status=new JLabel("第1关");
        toolbar.add(status);
//        this.add(toolbar, BorderLayout.NORTH);

        gameArea=new JPanel();
        gameArea.setLayout(null);
        tank=new JLabel(getTankIcon(39,false));
        tank.setBounds(0,0,19,19);
        gameArea.add(tank);
        this.add(gameArea, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new Game();
    }

    /**
     * 根据方向生成对应方向的坦克图标的方法
     * @param tank
     * @return
     */
    private ImageIcon getTankIcon(int tank,boolean dynamic){
            return new ImageIcon(Toolkit.getDefaultToolkit().createImage("sources/images/"+tank+(dynamic?".gif":".png")).getScaledInstance(19, 19, Image.SCALE_DEFAULT));
    }

    /**
     * 根据键盘上按下的键负责移动坦克的方法
     * @param direction
     */
    public void moveTank(int direction){
        if(nowDirection==direction){//如果之前方向和现在运动方向一致则将坦克向前移动一个位置
            int nowX=tankX,nowY=tankY;
            if(direction==37){
                nowX=(nowX<=0)?0:(tankX-moveSpeed);
            }else if(direction==38){
                nowY=(nowY<=0)?0:(tankY-moveSpeed);
            }else if(direction==39){
                nowX=((nowX+19)>=596)?nowX:(tankX+moveSpeed);
            }else if(direction==40){
                nowY=((nowY+19)>=570)?nowY:(tankY+moveSpeed);
            }
            tankX=nowX;tankY=nowY;
            tank.setLocation(nowX,nowY);
            tank.setIcon(getTankIcon(direction,true));
        }else{//如果之前方向和现在运动方向不一致则让坦克转向
            tank.setIcon(getTankIcon(direction,true));
            nowDirection=direction;
        }
    }

}








/**** 格子版本
 public class Game  extends JFrame{
 private ArrayList<JLabel> points=new ArrayList<>();
 private JMenuBar menuBar;
 private JMenu  game;
 private JMenuItem  start,pause,reStart,saveScore,exit;
 private JPanel  gameArea,toolbar;
 private JLabel  status;
 private TankThread  t;
 private int nowDirection=39;//记录当前坦克的运动方向 （默认坦克方向是向右）
 private int nowPosition=0;//记录坦克当前所处的位置
 public Game(){
 this.setSize(600,600);
 this.setLocationRelativeTo(null);
 this.setResizable(false);
 this.setVisible(true);
 this.setTitle("Tank war");
 this.setIconImage(Toolkit.getDefaultToolkit().createImage("sources/images/logo.gif"));
 //this.setLayout(new GridLayout(20,20,0,0));
 this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
 initMenus();
 initComponents();
 this.paintComponents(getGraphics());
 this.paintAll(getGraphics());
 this.addKeyListener(new KeyAdapter() {
@Override
public void keyPressed(KeyEvent e) {
moveTank(e.getKeyCode());

}
});
 }
 public void initMenus(){
 game=new JMenu("游戏");
 start=new JMenuItem("开始");
 pause=new JMenuItem("暂停");
 reStart=new JMenuItem("重新开始");
 saveScore=new JMenuItem("保存成绩");
 exit=new JMenuItem("退出");
 game.add(start);
 game.addSeparator();
 game.add(pause);
 game.addSeparator();
 game.add(reStart);
 game.addSeparator();
 game.add(saveScore);
 game.addSeparator();
 game.add(exit);
 game.addSeparator();
 menuBar=new JMenuBar();
 menuBar.add(game);
 this.setJMenuBar(menuBar);
 }

 public void initComponents(){
 toolbar=new JPanel();
 toolbar.setLayout(new GridLayout(1,10));
 status=new JLabel("第1关");
 toolbar.add(status);
 //        this.add(toolbar, BorderLayout.NORTH);

 gameArea=new JPanel();
 gameArea.setBorder(BorderFactory.createLineBorder(Color.black));
 gameArea.setLayout(new GridLayout(20,20,0,0));
 for(int n=0;n<400;n++){
 JLabel l=new JLabel();
 points.add(l);
 l.setBorder(BorderFactory.createLineBorder(Color.red));
 gameArea.add(l);
 }
 points.get(0).setIcon(getTankIcon("39"));
 this.add(gameArea, BorderLayout.CENTER);
 }

 public static void main(String[] args) {
 new Game();
 }
 private ImageIcon getTankIcon(String path){
 return new ImageIcon(Toolkit.getDefaultToolkit().createImage("sources/images/"+path+".gif").getScaledInstance(19, 19, Image.SCALE_DEFAULT));
 }

 public void moveTank(int direction){
 if(nowDirection==direction){//如果之前方向和现在运动方向一致则将坦克向前移动一个位置
 int nextPosition=0;
 if(direction==37){
 nextPosition=nowPosition-1;
 }else if(direction==38){
 nextPosition=nowPosition-20;
 }else if(direction==39){
 nextPosition=nowPosition+1;
 }else if(direction==40){
 nextPosition=nowPosition+20;
 }
 points.get(nowPosition).setIcon(null);
 points.get(nextPosition).setIcon(getTankIcon(direction+""));
 nowPosition=nextPosition;
 }else{//如果之前方向和现在运动方向不一致则让坦克转向
 points.get(nowPosition).setIcon(getTankIcon(direction+""));
 nowDirection=direction;
 }
 }


 }
 **/