public boolean isWon(int posX, int posY, String ico) {
		
				boolean b1=winInX(posX,posY,ico);
				boolean b2=winInY(posX,posY,ico);
				boolean b3=winInXY(posX,posY,ico);
				boolean b4=winInYX(posX,posY,ico);
				if(b1)return true;
				else if(b2)return true;
				else if(b3)return true;
				else if(b4)return true;
				return false;
		
		
		 			
				
	}
	
    
    public boolean winInX(int posX, int posY, String ico)  {  
	 
    	int i;
		int count=0;
		int x1=posY-WIN_COUNT+1;
		int x2=posY+WIN_COUNT-1;
		if((x1)<0) x1=0;
		if((x2)>14) x2=14;
		for (i=x1;i<=x2;i++) 
		{
			String[][] chess = chessboard.getBoard();
			
			if(chess[posX][i]==ico)count++;
			if(count==5) return true;
		}
		
		return false;
		 
    }
		
	public boolean winInY(int posX, int posY, String ico) {   
			int i;
			int count=0;
			int y1=posX-WIN_COUNT+1;
			int y2=posX+WIN_COUNT-1;
			if((y1)<0) y1=0;
			if((y2)>14) y2=14;
			for (i=y1;i<=y2;i++) 
			{
				String[][] chess = chessboard.getBoard();
				
				if(chess[i][posY]==ico)count++;
				if(count==5) return true;
		}
		
		return false;
		
	} 
		 
    public boolean winInXY(int posX, int posY, String ico) {   
		 
    	int i,j;
		int count=0;
		int x1=posY-WIN_COUNT+1;
		int x2=posY+WIN_COUNT-1;
		//int y1=posX-WIN_COUNT+1;
		//int y2=posX+WIN_COUNT-1;
		 
		if((x1)<0) x1=0;
		if((x2)>14) x2=14;
		
		//if((y1)<0) y1=0;
		//if((y2)>14) y2=14;
		
		for (i=x1;i<=x2;i++) 
		{   j=posX+i-posY;
		    
		    if(0<=j&&j<=14)
		    {  
		       
				String[][] chess = chessboard.getBoard();
				
				if(chess[j][i]==ico)count++;
				
				if(count==5) return true;
			}
		 
		}
		
		return false;
		 
	 } 
	 
    public boolean winInYX(int posX, int posY, String ico){  
    	 
    	int i,j;
		int count=0;
		int x1=posY-WIN_COUNT+1;
		int x2=posY+WIN_COUNT-1;
		//int y1=posX-WIN_COUNT+1;
		//int y2=posX+WIN_COUNT-1;
		 
		if((x1)<0) x1=0;
		if((x2)>14) x2=14;
		
		//if((y1)<0) y1=0;
		//if((y2)>14) y2=14;
		
		for (i=x1;i<=x2;i++) 
		{   j=posX-i+posY;
		    
		    if(0<=j&&j<=14)
		    {  
		       
				String[][] chess = chessboard.getBoard();
				
				if(chess[j][i]==ico)count++;
				
				if(count==5) return true;
			}
		 
		}
		
		return false;
	}