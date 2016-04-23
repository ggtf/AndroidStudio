package com.ggtf.gomoku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * Created by ggtf at 2015/11/6
 * Author:ggtf
 * Time:2015/11/6
 * Email:15170069952@163.com
 * ProjectName:Gomoku
 */

/**
 * 五子棋布局视图
 */
public class GomokuView extends View {

    private Paint linePaint;
    private Paint circlePaint;
    private List<Point> points;
    private List<Point> allPoints;
    private List<Integer> xPointSet;
    private List<Integer> yPointSet;
    private boolean isFirst = true;
    private int pieceSize;
    private int padding;
    private int pieceNum;
    /**
     * 判断那一方胜利;
     */
    private boolean isWhiteVictory;
    private boolean isBlackVictory;
    private boolean isWhite;
    /**
     * 白色棋的点的坐标集
     */
    private List<Point> whiteCoordinate;
    /**
     * 黑色棋的点的坐标集
     */
    private List<Point> blackCoordinate;
    private int radius;

    public GomokuView(Context context) {
        this(context, null);
    }

    public GomokuView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GomokuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initFields(context, attrs, defStyleAttr);
    }

    /**
     * 初始化属性和定义
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    private void initFields(Context context, AttributeSet attrs, int defStyleAttr) {
        pieceSize = 70;
        padding = 50;
        radius = 20;
//        pieceSize = 50;
//        padding = 50;
//        radius = 15;

        linePaint = new Paint();
        linePaint.setColor(Color.RED);
        linePaint.setStrokeWidth(3);
        linePaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(Color.WHITE);
        circlePaint.setStyle(Paint.Style.FILL);

        points = new LinkedList<>();
        allPoints = new LinkedList<>();
        xPointSet = new LinkedList<>();
        yPointSet = new LinkedList<>();
        whiteCoordinate = new LinkedList<>();
        blackCoordinate = new LinkedList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.GRAY);
        /**
         * 控件的宽高
         */
        int viewWidth = getWidth();
        int viewHeight = getHeight();
        /**
         * 水平和垂直方向的方格个数
         */
        int columnCount = (int) ((viewWidth - padding) / pieceSize);
        int rowCount = (int) ((viewHeight - padding) / pieceSize);
        pieceNum = columnCount * rowCount;
        int paintStrokeWidth = (int) linePaint.getStrokeWidth();
        /**
         * 依据网格个数来确定绘制的横线和纵线的长度
         */
        int lineHeight = (int) (rowCount * pieceSize + (rowCount + 1) * paintStrokeWidth) + 5;
        int lineWidth = (int) (columnCount * pieceSize + (rowCount + 1) * paintStrokeWidth) + 5;

        for (int i = 0; i < columnCount + 1; i++) {
            canvas.drawLine(
                    padding + (pieceSize * i),
                    padding,
                    padding + (pieceSize * i),
                    lineHeight,
                    linePaint);
            if (isFirst) {
                xPointSet.add(i, (int) (padding + (pieceSize * i)));
            }

        }
        for (int j = 0; j < rowCount + 1; j++) {
            canvas.drawLine(
                    padding,
                    padding + (pieceSize * j),
                    lineWidth,
                    padding + (pieceSize * j),
                    linePaint);
            if (isFirst) {
                yPointSet.add(j, (int) (padding + (pieceSize * j)));
            }

        }
        /**
         * 将绘制的交叉点保存起来;
         */
        if (isFirst) {
            if (yPointSet.size() > 0 && xPointSet.size() > 0) {
                if (yPointSet.size() == xPointSet.size()) {
                    int size = yPointSet.size();
                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            Point point = new Point(xPointSet.get(i), yPointSet.get(j));
                            allPoints.add(point);
                        }
                    }
                }
            }
            isFirst = false;
        }
        /**
         * 画圆
         */
        if (points.size() > 0) {
            int size = points.size();
            for (int i = 0; i < size; i++) {
                if (i % 2 == 0) {
                    circlePaint.setColor(Color.BLACK);
                } else {
                    circlePaint.setColor(Color.WHITE);
                }
                canvas.drawCircle(points.get(i).x, points.get(i).y, radius, circlePaint);
            }

        }
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        widthSize = widthSize <= heightSize ? widthSize : heightSize;
        heightSize = widthSize;
        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = false;
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            int x = (int) event.getX();
            int y = (int) event.getY();
//            计算属于哪一个交叉点
            int column = (x - padding) / pieceSize;
            if ((x - padding) % pieceSize > pieceSize / 2) {
                column++;
            }
            int row = (y - padding) / pieceSize;
            if ((y - padding) % pieceSize > pieceSize / 2) {
                row++;
            }
            int position = (int) (column * (Math.sqrt(pieceNum) + 1) + row);
            Point pointXY = allPoints.get(position);
            if (points.contains(pointXY)) {
                return false;
            }
            Point point = new Point(row, column);
            if (isWhite) {
                whiteCoordinate.add(point);
                isWhite = false;
            } else {
                blackCoordinate.add(point);
                isWhite = true;
            }
            points.add(pointXY);
//            强制刷新,执行onDraw();
            invalidate();
            checkVictory();
            result = true;
        }
        return false;
    }

    /**
     * 检测当前是否有一方胜利
     */
    private void checkVictory() {
        /**
         * 下的棋子数至少要大于5个才会有胜利基础
         */
        int blackNum = 1;
        int whiteNum = 1;

        if (whiteCoordinate.size() >= 5 || blackCoordinate.size() >= 5) {
            if (!isWhite) {
//             检测白棋是否5子成线
                Point point = whiteCoordinate.get(whiteCoordinate.size() - 1);
                /**
                 * 检测上下垂直方向是否5值成线
                 */
                whiteNum = whiteNum + isUpHadPoint(point) + isDownHadPoint(point);
                if (whiteNum == 5) {
                    isWhiteVictory = true;
                    Toast.makeText(getContext(), "isWhiteVictory", Toast.LENGTH_LONG).show();
                    return;
                }
                /**
                 * 检测水平左右方向是否5值成线
                 */
                whiteNum = 1;
                whiteNum = whiteNum + isRightHadPoint(point) + isLeftHadPoint(point);
                if (whiteNum == 5) {
                    isWhiteVictory = true;
                    Toast.makeText(getContext(), "isWhiteVictory", Toast.LENGTH_LONG).show();
                    return;
                }
                /**
                 * 检测右上左下方向是否5值成线
                 */
                whiteNum = 1;
                whiteNum = whiteNum + isRightUpHadPoint(point) + isLeftDownHadPoint(point);
                if (whiteNum == 5) {
                    isWhiteVictory = true;
                    Toast.makeText(getContext(), "isWhiteVictory", Toast.LENGTH_LONG).show();
                    return;
                }
                /**
                 * 检测左上右下方向是否5值成线
                 */
                whiteNum = 1;
                whiteNum = whiteNum + isRightDownHadPoint(point) + isLeftUpHadPoint(point);
                if (whiteNum == 5) {
                    isWhiteVictory = true;
                    Toast.makeText(getContext(), "isWhiteVictory", Toast.LENGTH_LONG).show();
                }

            } else {
//             检测黑棋是否5子成线
                Point point = blackCoordinate.get(blackCoordinate.size() - 1);
                /**
                 * 检测上下垂直方向是否5值成线
                 */
                blackNum = blackNum + isUpHadPoint(point) + isDownHadPoint(point);
                if (blackNum == 5) {
                    isBlackVictory = true;
                    Toast.makeText(getContext(), "isBlackVictory", Toast.LENGTH_LONG).show();
                    return;
                }
                /**
                 * 检测水平左右方向是否5值成线
                 */
                blackNum = 1;
                blackNum = blackNum + isRightHadPoint(point) + isLeftHadPoint(point);
                if (blackNum == 5) {
                    isBlackVictory = true;
                    Toast.makeText(getContext(), "isBlackVictory", Toast.LENGTH_LONG).show();
                    return;
                }
                /**
                 * 检测右上左下方向是否5值成线
                 */
                blackNum = 1;
                blackNum = blackNum + isRightUpHadPoint(point) + isLeftDownHadPoint(point);
                if (blackNum == 5) {
                    isBlackVictory = true;
                    Toast.makeText(getContext(), "isBlackVictory", Toast.LENGTH_LONG).show();
                    return;
                }
                /**
                 * 检测左上右下方向是否5值成线
                 */
                blackNum = 1;
                blackNum = blackNum + isRightDownHadPoint(point) + isLeftUpHadPoint(point);
                if (blackNum == 5) {
                    isBlackVictory = true;
                    Toast.makeText(getContext(), "isBlackVictory", Toast.LENGTH_LONG).show();
                }

            }
        }

    }

    /**
     * 返回值,左上有几个点
     *
     * @param point
     * @return
     */
    private int isLeftUpHadPoint(Point point) {
        int result = 0;
        int x = point.x;
        int y = point.y;
        Point leftUpPoint = new Point(x - 1, y - 1);
        if (!isWhite) {
            if (whiteCoordinate.contains(leftUpPoint)) {
                result = 1 + isLeftUpHadPoint(leftUpPoint);
            }
        } else {
            if (blackCoordinate.contains(leftUpPoint)) {
                result = 1 + isLeftUpHadPoint(leftUpPoint);
            }
        }
        return result;
    }

    /**
     * 返回值,右下有几个点
     *
     * @param point
     * @return
     */
    private int isRightDownHadPoint(Point point) {
        int result = 0;
        int x = point.x;
        int y = point.y;
        Point rightDownPoint = new Point(x + 1, y + 1);
        if (!isWhite) {
            if (whiteCoordinate.contains(rightDownPoint)) {
                result = 1 + isRightDownHadPoint(rightDownPoint);
            }
        } else {
            if (blackCoordinate.contains(rightDownPoint)) {
                result = 1 + isRightDownHadPoint(rightDownPoint);
            }
        }
        return result;
    }

    /**
     * 返回值,左下有几个点
     *
     * @param point
     * @return
     */
    private int isLeftDownHadPoint(Point point) {
        int result = 0;
        int x = point.x;
        int y = point.y;
        Point leftDownPoint = new Point(x - 1, y + 1);
        if (!isWhite) {
            if (whiteCoordinate.contains(leftDownPoint)) {
                result = 1 + isLeftDownHadPoint(leftDownPoint);
            }
        } else {
            if (blackCoordinate.contains(leftDownPoint)) {
                result = 1 + isLeftDownHadPoint(leftDownPoint);
            }
        }
        return result;
    }

    /**
     * 返回值,右上有几个点
     *
     * @param point
     * @return
     */
    private int isRightUpHadPoint(Point point) {
        int result = 0;
        int x = point.x;
        int y = point.y;
        Point rightUpPoint = new Point(x + 1, y - 1);
        if (!isWhite) {
            if (whiteCoordinate.contains(rightUpPoint)) {
                result = 1 + isRightUpHadPoint(rightUpPoint);
            }
        } else {
            if (blackCoordinate.contains(rightUpPoint)) {
                result = 1 + isRightUpHadPoint(rightUpPoint);
            }
        }
        return result;
    }

    /**
     * 返回值,往左有几个点
     *
     * @param point
     * @return
     */
    private int isLeftHadPoint(Point point) {
        int result = 0;
        int x = point.x;
        Point leftPoint = new Point(x - 1, point.y);
        if (!isWhite) {
            if (whiteCoordinate.contains(leftPoint)) {
                result = 1 + isLeftHadPoint(leftPoint);
            }
        } else {
            if (blackCoordinate.contains(leftPoint)) {
                result = 1 + isLeftHadPoint(leftPoint);
            }
        }

        return result;
    }

    /**
     * 返回值,往右有几个点
     *
     * @param point
     * @return
     */
    private int isRightHadPoint(Point point) {
        int result = 0;
        int x = point.x;
        Point rightPoint = new Point(x + 1, point.y);
        if (!isWhite) {
            if (whiteCoordinate.contains(rightPoint)) {
                result = 1 + isRightHadPoint(rightPoint);
            }
        } else {
            if (blackCoordinate.contains(rightPoint)) {
                result = 1 + isRightHadPoint(rightPoint);
            }
        }
        return result;
    }

    /**
     * 返回值,往下有几个点
     *
     * @param point
     * @return
     */
    private int isDownHadPoint(Point point) {
        int result = 0;
        int y = point.y;
        Point downPoint = new Point(point.x, y + 1);
        if (!isWhite) {
            if (whiteCoordinate.contains(downPoint)) {
                result = 1 + isDownHadPoint(downPoint);
            }
        } else {
            if (blackCoordinate.contains(downPoint)) {
                result = 1 + isDownHadPoint(downPoint);
            }
        }
        return result;
    }

    /**
     * 返回值,往上有几个点
     *
     * @param point
     * @return
     */
    private int isUpHadPoint(Point point) {
        int result = 0;
        int y = point.y;
        if (y > 0) {
            Point upPoint = new Point(point.x, y - 1);
            if (!isWhite) {
                if (whiteCoordinate.contains(upPoint)) {
                    result = 1 + isUpHadPoint(upPoint);
                }
            } else {
                if (blackCoordinate.contains(upPoint)) {
                    result = 1 + isUpHadPoint(upPoint);
                }
            }
        }
        return result;
    }
}
