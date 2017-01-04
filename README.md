# StickyHeadRecyclerView
 <blockquote>
用这个方式实现有很大的局限性, 粗糙的解释一下, 在recyclerView上套一层和itemView 上一样的head的布局, 再滑动的时候动态设置套在recyclerView上布局即可(见下图)
 </blockquote>


 
![normalHead.png](http://upload-images.jianshu.io/upload_images/2828031-6c5e1effd6d55fef.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


 <blockquote>
标题栏以下就是recyclerView+normalHead 的布局, item的布局的head必须要跟normalHead的布局一模一样,如果不一样则实现不了需求.
 </blockquote>

```
 rvContent.addOnScrollListener(new RecyclerView.OnScrollListener() {        
      @Override      
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {      
              super.onScrollStateChanged(recyclerView, newState);         
             // 当recyclerView的滑动改变改变的时候 实时拿到它的高度         
             headHeight = normalHead.getHeight();    
    }        
      @Override    
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {       
               super.onScrolled(recyclerView, dx, dy);  
          // 根据当前的position的下一个拿到view         
           View itemView = linearLayoutManager.findViewByPosition(mCurrentPosition + 1);        
          if (itemView != null) {             
           // 通过itemView 顶部y 坐标  判断是否小于或者等于head的底部y坐标,成立的话,就说明itemViewHead已经和normalHead开始接触了.
          // 就开始对NormalHead 进行操作        
             if (itemView.getTop() <= headHeight) {          
// 下面有详细解释
                 normalHead.setY(-(headHeight - itemView.getTop()));       
             } else {                       
                 normalHead.setY(0);                 
        }      
      }       
             //拿到当前第一个显示的item的position          
            int currentPosition = linearLayoutManager.findFirstVisibleItemPosition();  
          // 如果不等于的话, 就说明当前mCurrentPosition 的 (上滑)上一个已经隐藏或者是(下滑)下一个已经完全显示了,就要做重新赋值的操作了. 
            if (mCurrentPosition != currentPosition) {              
              mCurrentPosition = currentPosition;              
              normalHead.setY(0);           
             // 拿到当前item的head值,显示在normalHead上     
               normalHead.setText(data.get(mCurrentPosition ).text + (mCurrentPosition + 1));      
      }     
   }   
 });
}
``` 

 <blockquote>
  **主要的逻辑代码在于**

  1, ` View itemView = linearLayoutManager.findViewByPosition(mCurrentPosition + 1);   ` 拿到当前position的下一个item的view
 2 .   ` if (itemView.getTop() <= headHeight) 
   normalHead.setY(-(headHeight - itemView.getTop()));     `
 判断下一个的item的top值是不是小于normalHead的高度,

如果成立
则分成两种情况 
 上滑 : -(headHeight - itemView.getTop())将为负值, normalHead则逐渐隐藏
下滑 :  -(headHeight - itemView.getTop())将为正值, normalHead不断的往下移,当前显示的item的head不断被滑下

如果不成立  则 说明 上滑或者下滑的top值已经大于了normalHead的高度了, 则说明不需要移动了,将normalHead固定在顶部
 `normalHead.setY(0);     ` 

3,  `  int currentPosition = linearLayoutManager.findFirstVisibleItemPosition();  ` 拿到当前显示的第一个position
4    ` if (mCurrentPosition != currentPosition) `
如果不等于的话, 就说明当前mCurrentPosition 的 (上滑)normalHead已经完全隐藏或者是(下滑)itemView的Head已经完全显示了,就要对normalHead重新赋值并且固定在顶部了    

 </blockquote>

 <blockquote>

这段代码难度不大,但是理解起来还是有点儿复杂,主要代码就是这么一段,实际上就是监听recyclerView的滑动,当normalHead和itemView的head接触的时候,隐藏和显示并且对normalHead的值进行操作, 说白了,就是一个偷梁换柱的过程,有两个相同的head一起盖着呢...

所以说 这个实现过程虽然简单, 就是有其局限性就是normalHead和itemView的head的布局都要一模一样, 不然是实现不了的

希望我的注释能够帮到同学们,如果有些难以理解, 或者我的注释写的不够完善,(我表达不大行....感觉好多东西都是只可意会不可言传.....) 直接下载项目看一下效果就能明白,或者到http://www.jianshu.com/p/911b269f6b9c提出疑问.我会尽我所能解答.
 </blockquote>
