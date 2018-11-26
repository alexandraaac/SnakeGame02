package br.unifor.cct;
 //alexandraac 
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game implements KeyListener {

    private static final String GAME_TITLE = "Snake Game";
    private static final int SQUARE_SIZE = 50;
    private JFrame frame;
    private Canvas canvas;
    private Grid grid;
    private List<Square> snake;
    private Square food;
    private String chave;
    private int tComida = 0;
    private int tAndar = 0;

    public Game(int width, int height) {

        // Cria a janela do jogo com as devidas propriedades
        this.frame = new JFrame(GAME_TITLE);
        this.frame.getContentPane().setPreferredSize(new Dimension(width, height));
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.addKeyListener(this);
        this.frame.setVisible(true);
        this.chave = "Direita";
 
        // Cria um canvas do mesmo tamanho da janela
        this.canvas = new Canvas(frame.getWidth(), frame.getHeight());

        // Cria o objeto que irá desenhar a grade de jogo
        this.grid = new Grid(frame.getWidth(), frame.getHeight());

        // Cria o objeto que irá representar a comida do snake
        this.food = new Square(SQUARE_SIZE, Color.RED);

        // Cria a lista de objetos Square que representará o snake
        this.snake = new ArrayList<>();
        Square square = new Square(SQUARE_SIZE, Color.GREEN);
        square.setX(5);
        square.setY(5);
        
        this.snake.add(square);


        // Adiciona o canvas na janela
        this.frame.add(canvas);

    }

    public void start() {

        while (true) {
        	Graphics2D g = canvas.getBuffer().createGraphics();
         	
        	if(snake.get(0).getX()<1 || snake.get(0).getY()<1 || snake.get(0).getY()>10 || snake.get(0).getX()>10) {
        	
        		 
        		try { 
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
       		
       		 g.clearRect(0, 0, frame.getWidth(), frame.getHeight());
             canvas.repaint();

        		snake.clear();
        		Square square1 = new Square(SQUARE_SIZE, Color.GREEN);
        		Random cdn = new Random();
        		square1.setX(cdn.nextInt(5)+2);
        		square1.setY(cdn.nextInt(5)+2);
        		snake.add(square1);
 
        		food.setX(cdn.nextInt(9)+1);
        		food.setY(cdn.nextInt(9)+1);
        		if(food.getX() == snake.get(0).getX() && food.getY() == snake.get(0).getY()) {
        		while(food.getX() == snake.get(0).getX() && food.getY() == snake.get(0).getY()) {
        			food.setX(cdn.nextInt(9)+1);
            		food.setY(cdn.nextInt(9)+1);
        		}
        		} 
        	}
 
        	for (int i = 2; i < snake.size(); i++) {
                if(snake.get(0).getX() == snake.get(i).getX() && snake.get(0).getY() == snake.get(i).getY() && snake.size() > 2) {
            		

                 
              		 try { 
                           Thread.sleep(2000);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
              		
                	
                	snake.clear();
            		Square square1 = new Square(SQUARE_SIZE, Color.GREEN);
            		Random cdn = new Random();
            		square1.setX(cdn.nextInt(5)+2);
            		square1.setY(cdn.nextInt(5)+2);
            		snake.add(square1);

            		food.setX(cdn.nextInt(9)+1);
            		food.setY(cdn.nextInt(9)+1);
            		if(food.getX() == snake.get(0).getX() && food.getY() == snake.get(0).getY()) {
            		while(food.getX() == snake.get(0).getX() && food.getY() == snake.get(0).getY()) {
            			food.setX(cdn.nextInt(9)+1);
                		food.setY(cdn.nextInt(9)+1);
            		}
                }
                }
            }
        	
        
        	
        	
        	if(tComida == 600) {
        		Random cdn = new Random();

        		food.setX(cdn.nextInt(9)+1);
        		food.setY(cdn.nextInt(9)+1);
        		tComida = 0;
        	}
            // Cria  objeto de desenho
            
            // Limpando a tela
            g.setBackground(Color.WHITE);
            g.clearRect(0, 0, frame.getWidth(), frame.getHeight());
            
           
            // Desenha o grade do jogo
            grid.draw(g);
         	

            // Desenha a comida do snake
            
            food.draw(g);

       

            // Desenha o snake
            for (int i = 0; i < snake.size(); i++) {
                snake.get(i).draw(g);
            }

            canvas.repaint();

            try { 
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           tComida++;
           tAndar++;
            
            checar();

            if(tAndar == 20) {
            		andar();
            		tAndar = 0;
            }
            
            
        }

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
    	
    	int actualX = snake.get(0).getX();
    	int actualY = snake.get(0).getY();
    	Square aux = snake.get(snake.size()-1);
    	List<Square> snake1 = new ArrayList<Square>();
    	
        switch (keyEvent.getKeyCode()) {

            case KeyEvent.VK_LEFT:
            	
            	
            	actualY = snake.get(0).getY();
            	if(!chave.equals("Direita") || snake.size()==1) {
            		
            	
            	
            	
                chave = "Esquerda";
                
                
                aux = snake.get(snake.size() -1);
                
                aux.setY(actualY - 1 );
                aux.setX(snake.get(0).getX());
                
                snake.remove(snake.size() -1);
                
                snake1 = new ArrayList<Square>();
                snake1.add(aux);
                snake1.addAll(snake);
                
                snake.clear();
                snake.addAll(snake1);
        }
                
               
                break;

            case KeyEvent.VK_RIGHT:
            	if(!chave.equals("Esquerda")|| snake.size()==1) {
            		
            	
                chave = "Direita";
                
                
            		actualY = snake.get(0).getY();
                
                aux = snake.get(snake.size() -1);
                
                aux.setY(actualY + 1 );
                aux.setX(snake.get(0).getX());
                
                snake.remove(snake.size() -1);
                
                snake1 = new ArrayList<Square>();
                snake1.add(aux);
                snake1.addAll(snake);
                
                snake.clear();
                snake.addAll(snake1);
                
               

        }
                break;

            case KeyEvent.VK_UP:
            	
            	if(!chave.equals("Abaixo")|| snake.size()==1) {
            		
            	
                chave = "Acima";
                
            		actualX = snake.get(0).getX();
                
                aux = snake.get(snake.size() -1);
                
                aux.setX(actualX - 1);
                aux.setY(snake.get(0).getY());
                
                snake.remove(snake.size() -1);
                
                snake1 = new ArrayList<Square>();
                snake1.add(aux);
                snake1.addAll(snake);
                
                snake.clear();
                snake.addAll(snake1);
                
            
               
        }
                break;

            case KeyEvent.VK_DOWN:
            	if(!chave.equals("Acima")|| snake.size()==1) {
            		

            	
                chave = "Abaixo";
               
                actualX = snake.get(0).getX();
                
                aux = snake.get(snake.size() -1);

                aux.setX(actualX + 1 );
                aux.setY(snake.get(0).getY());
                
                snake.remove(snake.size() -1);
                
                snake1 = new ArrayList<Square>();
                snake1.add(aux);
                snake1.addAll(snake);
                
                snake.clear();
                snake.addAll(snake1);
             
            	} 
                break;

        }

    }

   
    public void checar() {
 
    	Square aux = snake.get(snake.size()-1);
 
    	
       

            if(chave.equals("Esquerda")) {
            	
                if(aux.getX() == food.getX() && aux.getY() == food.getY()) {
                	Square square1 = new Square(SQUARE_SIZE, Color.GREEN);
                	square1.setX(snake.get(snake.size()-1).getX());
                	square1.setY(snake.get(snake.size()-1).getY());
                	snake.add(square1);

                	Random cdn = new Random();
                	food.setX(cdn.nextInt(9)+1);
                	food.setY(cdn.nextInt(9)+1);
                }





            }
                if(chave.equals("Direita")) {
               
                
                if(aux.getX() == food.getX() && aux.getY() == food.getY()) {
                	Square square1 = new Square(SQUARE_SIZE, Color.GREEN);
                	square1.setX(snake.get(snake.size()-1).getX());
                	square1.setY(snake.get(snake.size()-1).getY());
                	snake.add(square1);

                	Random cdn = new Random();
                	food.setX(cdn.nextInt(9)+1);
                	food.setY(cdn.nextInt(9)+1);
                }



                }
                if(chave.equals("Acima")) {
               
                


                if(aux.getX() == food.getX() && aux.getY() == food.getY()) {
                	Square square1 = new Square(SQUARE_SIZE, Color.GREEN);
                	square1.setX(snake.get(snake.size()-1).getX());
                	square1.setY(snake.get(snake.size()-1).getY());
                	snake.add(square1);

                	Random cdn = new Random();
                	food.setX(cdn.nextInt(9)+1);
                	food.setY(cdn.nextInt(9)+1);
                }



                }

                if(chave.equals("Abaixo")) {
               
               
                

                if(aux.getX() == food.getX() && aux.getY() == food.getY()) {
                	Square square1 = new Square(SQUARE_SIZE, Color.GREEN);
                	square1.setX(snake.get(snake.size()-1).getX());
                	square1.setY(snake.get(snake.size()-1).getY());
                	snake.add(square1);

                	Random cdn = new Random();
                	food.setX(cdn.nextInt(9)+1);
                	food.setY(cdn.nextInt(9)+1);
                }



                }

        

    }
    
    
   
 public void andar() {
   
    	
    	int actualX = snake.get(0).getX();
    	int actualY = snake.get(0).getY();
    	Square aux = snake.get(snake.size()-1);
    	List<Square> snake1 = new ArrayList<Square>();
    	
       

            if(chave.equals("Esquerda")) {
            	
           
            	actualY = snake.get(0).getY();
            	
            	
             
                
                
                aux = snake.get(snake.size() -1);
                
                aux.setY(actualY - 1 );
                aux.setX(snake.get(0).getX());
                
                snake.remove(snake.size() -1);
                
                snake1 = new ArrayList<Square>();
                snake1.add(aux);
                snake1.addAll(snake);
                
                snake.clear();
                snake.addAll(snake1);
                

                if(aux.getX() == food.getX() && aux.getY() == food.getY()) {
                	Square square1 = new Square(SQUARE_SIZE, Color.GREEN);
                	square1.setX(snake.get(snake.size()-1).getX());
                	square1.setY(snake.get(snake.size()-1).getY());
                	snake.add(square1);

                	Random cdn = new Random();
                	food.setX(cdn.nextInt(9)+1);
                	food.setY(cdn.nextInt(9)+1);
                }




            }
                if(chave.equals("Direita")) {
               
                
            		actualY = snake.get(0).getY();
                
                aux = snake.get(snake.size() -1);
                
                aux.setY(actualY + 1 );
                aux.setX(snake.get(0).getX());
                
                snake.remove(snake.size() -1);
                
                snake1 = new ArrayList<Square>();
                snake1.add(aux);
                snake1.addAll(snake);
                
                snake.clear();
                snake.addAll(snake1);
                
                if(aux.getX() == food.getX() && aux.getY() == food.getY()) {
                	Square square1 = new Square(SQUARE_SIZE, Color.GREEN);
                	square1.setX(snake.get(snake.size()-1).getX());
                	square1.setY(snake.get(snake.size()-1).getY());
                	snake.add(square1);

                	Random cdn = new Random();
                	food.setX(cdn.nextInt(9)+1);
                	food.setY(cdn.nextInt(9)+1);
                }



                }
                if(chave.equals("Acima")) {
               
                
            		actualX = snake.get(0).getX();
                
                aux = snake.get(snake.size() -1);
                
                aux.setX(actualX - 1);
                aux.setY(snake.get(0).getY());
                
                snake.remove(snake.size() -1);
                
                snake1 = new ArrayList<Square>();
                snake1.add(aux);
                snake1.addAll(snake);
                
                snake.clear();
                snake.addAll(snake1);


                if(aux.getX() == food.getX() && aux.getY() == food.getY()) {
                	Square square1 = new Square(SQUARE_SIZE, Color.GREEN);
                	square1.setX(snake.get(snake.size()-1).getX());
                	square1.setY(snake.get(snake.size()-1).getY());
                	snake.add(square1);

                	Random cdn = new Random();
                	food.setX(cdn.nextInt(9)+1);
                	food.setY(cdn.nextInt(9)+1);
                }



                }

                if(chave.equals("Abaixo")) {
               
               
                actualX = snake.get(0).getX();
                
                aux = snake.get(snake.size() -1);

                aux.setX(actualX + 1 );
                aux.setY(snake.get(0).getY());
                
                snake.remove(snake.size() -1);
                
                snake1 = new ArrayList<Square>();
                snake1.add(aux);
                snake1.addAll(snake);
                
                snake.clear();
                snake.addAll(snake1);
                
                

                if(aux.getX() == food.getX() && aux.getY() == food.getY()) {
                	Square square1 = new Square(SQUARE_SIZE, Color.GREEN);
                	square1.setX(snake.get(snake.size()-1).getX());
                	square1.setY(snake.get(snake.size()-1).getY());
                	snake.add(square1);

                	Random cdn = new Random();
                	food.setX(cdn.nextInt(9)+1);
                	food.setY(cdn.nextInt(9)+1);
                }


                }

        

    }
    
    


    @Override
    public void keyTyped(KeyEvent keyEvent) {
    	  try { 
              Thread.sleep(10000);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
    }

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	 
	
}

