package com.OmidMosalmani.algorithmproject;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

public class InputsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputs);

        final ToggleButton addEnd = findViewById(R.id.addEndBtn);
        final ToggleButton addWall = findViewById(R.id.addWallBtn);
        final ToggleButton addPath = findViewById(R.id.addPathBtn);
        final ToggleButton addStr = findViewById(R.id.toggleButton);
        final Button next = findViewById(R.id.nextStepBtn);
        LinearLayout ly = findViewById(R.id.linearLey);

        Bundle bundle = getIntent().getExtras();
        final int n = bundle.getInt("length");
        final int m = bundle.getInt("width");
        final int[][] type = new int[n][m];
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = (displayMetrics.heightPixels - 500) / n;
        int width = displayMetrics.widthPixels / m;
        int btnSize = Math.min(height, width);
        LinearLayout[] lines = new LinearLayout[n];

        final Button[][] btnArr = new Button[n][m];
        for (int i = 0; i < n; i++) {
            lines[i] = new LinearLayout(this);
            lines[i].setOrientation(LinearLayout.HORIZONTAL);
            for (int j = 0; j < m; j++) {
                btnArr[i][j] = new Button(this);
                lines[i].addView(btnArr[i][j], new android.view.ViewGroup.LayoutParams(btnSize, btnSize));
            }
            ly.addView(lines[i]);
        }

        final Button defbtn = new Button(this);
        final int[] numOfEnd = {-2};
        final int[] numOfPath = {2};

        final boolean[] hasStart = {false};
        addStr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEnd.setChecked(false);
                addPath.setChecked(false);
                addWall.setChecked(false);
                if (addStr.isChecked()) {
                    for (int i = 0; i < n && addStr.isChecked(); i++)
                        for (int j = 0; j < m && addStr.isChecked(); j++) {
                            final int finalI = i;
                            final int finalJ = j;
                            btnArr[i][j].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(!addStr.isChecked())
                                        return;
                                    if (type[finalI][finalJ] == 1) {
                                        btnArr[finalI][finalJ].setBackground(defbtn.getBackground());
                                        type[finalI][finalJ] = 0;
                                        hasStart[0] = false;
                                    }else if(type[finalI][finalJ]<-1) {
                                        btnArr[finalI][finalJ].setBackgroundColor(Color.GREEN);
                                        type[finalI][finalJ] = 1;
                                        numOfEnd[0]++;
                                        hasStart[0] = true;
                                    }else if(type[finalI][finalJ]>1) {
                                        btnArr[finalI][finalJ].setBackgroundColor(Color.GREEN);
                                        type[finalI][finalJ] = 1;
                                        numOfPath[0]--;
                                        hasStart[0] = true;
                                    }else if(type[finalI][finalJ]==-1){
                                        btnArr[finalI][finalJ].setBackgroundColor(Color.GREEN);
                                        type[finalI][finalJ] = 1;
                                        hasStart[0] = true;
                                    } else {
                                        for (int a = 0; a < n; a++)
                                            for (int b = 0; b < m; b++)
                                                if (type[a][b] == 1) {
                                                    btnArr[a][b].setBackground(defbtn.getBackground());
                                                    type[a][b] = 0;
                                                }
                                        btnArr[finalI][finalJ].setBackgroundColor(Color.GREEN);
                                        type[finalI][finalJ] = 1;
                                        hasStart[0] = true;
                                    }
                                    addStr.setChecked(false);
                                }
                            });
                        }
                }else {
                    doNothing(n, m, btnArr);
                }
            }
        });


        addEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStr.setChecked(false);
                addPath.setChecked(false);
                addWall.setChecked(false);
                if (addEnd.isChecked()) {
                    for (int i = 0; i < n; i++)
                        for (int j = 0; j < m; j++) {
                            final int finalI = i;
                            final int finalJ = j;
                            btnArr[i][j].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    if (type[finalI][finalJ] < -1) {
                                        btnArr[finalI][finalJ].setBackground(defbtn.getBackground());
                                        type[finalI][finalJ] = 0;
                                        numOfEnd[0]++;
                                    }else if(type[finalI][finalJ]==1) {
                                        btnArr[finalI][finalJ].setBackgroundColor(Color.RED);
                                        type[finalI][finalJ] = numOfEnd[0]--;
                                        hasStart[0] = true;
                                    }else if(type[finalI][finalJ]>1) {
                                        btnArr[finalI][finalJ].setBackgroundColor(Color.RED);
                                        type[finalI][finalJ] = numOfEnd[0]--;
                                        numOfPath[0]--;
                                    } else if(type[finalI][finalJ]==-1){
                                        btnArr[finalI][finalJ].setBackgroundColor(Color.RED);
                                        type[finalI][finalJ] = numOfEnd[0]--;
                                    } else {
                                        btnArr[finalI][finalJ].setBackgroundColor(Color.RED);
                                        type[finalI][finalJ] = numOfEnd[0]--;
                                    }

                                }
                            });
                        }
                }else {
                    doNothing(n, m, btnArr);
                }
            }
        });


        addWall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEnd.setChecked(false);
                addPath.setChecked(false);
                addStr.setChecked(false);
                if (addWall.isChecked()) {
                    for (int i = 0; i < n; i++)
                        for (int j = 0; j < m; j++) {
                            final int finalI = i;
                            final int finalJ = j;
                            btnArr[i][j].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (type[finalI][finalJ] == -1) {
                                        btnArr[finalI][finalJ].setBackground(defbtn.getBackground());
                                        type[finalI][finalJ] = 0;
                                    }else if(type[finalI][finalJ]<-1) {
                                        btnArr[finalI][finalJ].setBackgroundColor(Color.BLACK);
                                        type[finalI][finalJ] = -1;
                                        numOfEnd[0]++;
                                    } else if(type[finalI][finalJ]==1) {
                                        btnArr[finalI][finalJ].setBackgroundColor(Color.BLACK);
                                        type[finalI][finalJ] = -1;
                                        hasStart[0] = false;
                                    } else if(type[finalI][finalJ]>1){
                                        btnArr[finalI][finalJ].setBackgroundColor(Color.BLACK);
                                        type[finalI][finalJ] = -1;
                                        numOfPath[0]--;
                                    } else {
                                        btnArr[finalI][finalJ].setBackgroundColor(Color.BLACK);
                                        type[finalI][finalJ] = -1;
                                    }
                                }
                            });
                        }
                }else {
                    doNothing(n, m, btnArr);
                }
            }
        });

        addWall.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                addEnd.setChecked(false);
                addPath.setChecked(false);
                addStr.setChecked(false);
                for (int i = 0; i < n; i++)
                    for (int j = 0; j < m; j++)
                        if (type[i][j] == 0) {
                            type[i][j] = -1;
                            btnArr[i][j].setBackgroundColor(Color.BLACK);
                        }
                return true;
            }
        });

        addPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEnd.setChecked(false);
                addStr.setChecked(false);
                addWall.setChecked(false);
                if (addPath.isChecked()) {
                    for (int i = 0; i < n; i++)
                        for (int j = 0; j < m; j++) {
                            final int finalI = i;
                            final int finalJ = j;
                            btnArr[i][j].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (type[finalI][finalJ] > 1) {
                                        btnArr[finalI][finalJ].setBackground(defbtn.getBackground());
                                        type[finalI][finalJ] = 0;
                                        numOfPath[0]--;
                                    }else if(type[finalI][finalJ]<-1) {
                                        btnArr[finalI][finalJ].setBackgroundColor(Color.WHITE);
                                        type[finalI][finalJ] = numOfPath[0]++;
                                        numOfEnd[0]++;
                                    } else if(type[finalI][finalJ]==-1) {
                                        btnArr[finalI][finalJ].setBackgroundColor(Color.WHITE);
                                        type[finalI][finalJ] = numOfPath[0]++;
                                    } else if(type[finalI][finalJ]==1){
                                        btnArr[finalI][finalJ].setBackgroundColor(Color.WHITE);
                                        type[finalI][finalJ] = numOfPath[0]++;
                                        hasStart[0] = false;
                                    } else {
                                        btnArr[finalI][finalJ].setBackgroundColor(Color.WHITE);
                                        type[finalI][finalJ] = numOfPath[0]++;
                                    }
                                }
                            });
                        }
                }else {
                    doNothing(n, m, btnArr);
                }
            }
        });

        addPath.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                addEnd.setChecked(false);
                addStr.setChecked(false);
                addWall.setChecked(false);
                for (int i = 0; i < n; i++)
                    for (int j = 0; j < m; j++)
                        if (type[i][j] == 0) {
                            type[i][j] = numOfPath[0]++;
                            btnArr[i][j].setBackgroundColor(Color.WHITE);
                        }
                return true;
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = true;
                for (int i = 0; i < n && flag; i++)
                    for (int j = 0; j < m; j++) {
                        if (type[i][j] == 0) {
                            Toast.makeText(getApplicationContext(), "please set kind of all of points", Toast.LENGTH_SHORT).show();
                            flag = false;
                            break;
                        }

                    }
                if (!hasStart[0] && flag) {
                    Toast.makeText(getApplicationContext(), "please add an start point", Toast.LENGTH_SHORT).show();
                    flag = false;
                }
                if (numOfEnd[0] == -2 && flag) {
                    Toast.makeText(getApplicationContext(), "please add an end point", Toast.LENGTH_SHORT).show();
                    flag = false;
                }

                if (flag) {
                    int s = (-1 * (numOfEnd[0])) - 1;
                    Graph g = new Graph(numOfPath[0] + s + 1);
                    for (int i = 0; i < n; i++)
                        for (int j = 0; j < m; j++)
                            if (type[i][j] < -1 || type[i][j] > 0) {

                                if (i != 0 && (type[i - 1][j] < -1 || type[i - 1][j] > 0))
                                    g.addEdge(type[i][j] + s, type[i - 1][j] + s);

                                if (j != 0 && (type[i][j - 1] < -1 || type[i][j - 1] > 0))
                                    g.addEdge(type[i][j] + s, type[i][j - 1] + s);

                                if (j != m - 1 && (type[i][j + 1] < -1 || type[i][j + 1] > 0))
                                    g.addEdge(type[i][j] + s, type[i][j + 1] + s);

                                if (i != n - 1 && (type[i + 1][j] < -1 || type[i + 1][j] > 0))
                                    g.addEdge(type[i][j] + s, type[i + 1][j] + s);

                            }
                    ArrayList<Integer> min = g.ShortestDistance(s + 1, 0);
                    for (int i = 1; i <= s - 2 && min != null; i++) {
                        ArrayList<Integer> res = g.ShortestDistance(s + 1, i);
                        if (res!=null && res.size() < min.size())
                            min = res;
                    }
                    if (min == null)
                        Toast.makeText(getApplicationContext(), "Given source and destination are not connected", Toast.LENGTH_SHORT).show();
                    else
                        for (int i = 0; i < n; i++)
                            for (int j = 0; j < m; j++) {
                                for (int k = 1; k < min.size() - 1; k++) {
                                    if (type[i][j] == min.get(k) - s)
                                        btnArr[i][j].setBackgroundColor(Color.BLUE);
                                }
                                if (type[i][j] == min.get(0) - s)
                                    btnArr[i][j].setBackgroundColor(getResources().getColor(R.color.colorOrange));
                            }

                }

            }
        });

    }

    private void doNothing(int n, int m, final Button[][] btnArr) {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                final int finalI = i;
                final int finalJ = j;
                btnArr[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btnArr[finalI][finalJ]=btnArr[finalI][finalJ];
                    }
                });
            }
    }
}
