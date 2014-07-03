//F74006030
//黃均暉
//TOC Project

import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.attribute.standard.Sides;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Proj {

	public static void main(String[] args)throws Exception {
		PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
		System.out.println("Start");
		int l=0, k=0;
		
		if(args.length<3){
			System.out.println("參數錯誤");
			return;
		}
		k=Integer.parseInt(args[1]);
		l=Integer.parseInt(args[2]);
		JSONArray input = null;
		JSONObject json;
		ArrayList<ArrayList<String>> data;
		ArrayList<ArrayList<ArrayList<Integer>>> value;
		
		
		try {
			input=getJSONFromURL(args[0]);
		} catch (Exception e) {
		}
		
		json=new JSONObject(new JSONTokener(input.get(0).toString()));
		String[] keys=JSONObject.getNames(json);
		
		String temp;
		int tempIndex;
		data=new ArrayList<ArrayList<String>>();
		value=new ArrayList<ArrayList<ArrayList<Integer>>>();
		for(int a=0;a<keys.length;a++){
			data.add(new ArrayList<String>());
			value.add(new ArrayList<ArrayList<Integer>>());
		}
		
		int length=input.length();
		int[][] appear=new int[length][keys.length];
		for(int a=0;a<length;a++){
			json= new JSONObject(new JSONTokener(input.get(a).toString()));
			for(int b=0;b<keys.length;b++){
				
				temp=json.optString(keys[b]);
				if(json.isNull(keys[b])){
					temp="null";
				}
				tempIndex=data.get(b).indexOf(temp);
				if(tempIndex==-1){
					data.get(b).add(temp);
					ArrayList<Integer> tempArrayList=new ArrayList<Integer>();
					tempArrayList.add(a);
					appear[a][b]=value.get(b).size();
					value.get(b).add(tempArrayList);
					
				}else{
					appear[a][b]=tempIndex;
					value.get(b).get(tempIndex).add(a);
				}
			}
		}
		if(l==2){
			ArrayList<ArrayList<Integer[][]>> l2;
			l2 =new ArrayList<ArrayList<Integer[][]>>();
			for(int a=0;a<5;a++){
				ArrayList<Integer[][]> templ2=new ArrayList<Integer[][]>();
				for(int b=0;b<=a;b++){
					templ2.add(null);
				}
				l2.add(templ2);
			}
			for(int a=0;a<5;a++){
				for(int b=a+1;b<5;b++){
					Integer[][] tempInt=new Integer[data.get(a).size()][data.get(b).size()];
					for(int c=0;c<length;c++){
						if(tempInt[appear[c][a]][appear[c][b]]==null){
							tempInt[appear[c][a]][appear[c][b]]=0;
						}
						tempInt[appear[c][a]][appear[c][b]]++;
					}
					l2.get(a).add(tempInt);
				}
			}
			int len=0;
			for(int a=0;a<5;a++){
				for(int b=a+1;b<5;b++){
					for(int c=0;c<l2.get(a).get(b).length;c++){
						len+=l2.get(a).get(b)[c].length;
					}
				}
			}
			int[] t1=new int[len];
			int[] t2=new int[len];
			int[] t1v=new int[len];
			int[] t2v=new int[len];
			int[] l2val=new int[len];
			int head=0;
			for(int a=0;a<5;a++){
				for(int b=a+1;b<5;b++){
					for(int c=0;c<l2.get(a).get(b).length;c++){
						for(int d=0;d<l2.get(a).get(b)[c].length;d++){
							if(l2.get(a).get(b)[c][d]==null){
								l2val[head]=0;
							}else{
								l2val[head]=l2.get(a).get(b)[c][d];
							}
							t1[head]=a;
							t2[head]=b;
							t1v[head]=c;
							t2v[head]=d;
							head++;
						}
					}
				}
			}
			for (int i = len-1; i > 0; --i){
	            for (int j = 0; j < i; ++j){
	                if (l2val[j] < l2val[j + 1]){
	                    int tmp=l2val[j];
	                    l2val[j]=l2val[j+1];
	                    l2val[j+1]=tmp;
	                    tmp=t1[j];
	                    t1[j]=t1[j+1];
	                    t1[j+1]=tmp;
	                    tmp=t2[j];
	                    t2[j]=t2[j+1];
	                    t2[j+1]=tmp;
	                    tmp=t1v[j];
	                    t1v[j]=t1v[j+1];
	                    t1v[j+1]=tmp;
	                    tmp=t2v[j];
	                    t2v[j]=t2v[j+1];
	                    t2v[j+1]=tmp;
	                }
	            }
			}
			for(int a=0;a<k||l2val[a]==l2val[k-1];a++){
				writer.println(keys[t1[a]]+":"+data.get(t1[a]).get(t1v[a])+","+keys[t2[a]]+":"+data.get(t2[a]).get(t2v[a])+";"+l2val[a]);
			}
			writer.close();
			System.out.println("done");
		}
		if(l==3){
			ArrayList<ArrayList<ArrayList<Integer[][][]>>> l3;
			l3 =new ArrayList<ArrayList<ArrayList<Integer[][][]>>>();
			for(int a=0;a<5;a++){
				ArrayList<ArrayList<Integer[][][]>> templ31=new ArrayList<ArrayList<Integer[][][]>>();
				for(int b=0;b<5;b++){
					ArrayList<Integer[][][]> templ32=new ArrayList<Integer[][][]>();
					for(int c=0;c<5;c++){
						templ32.add(null);
					}
					templ31.add(templ32);
				}
				l3.add(templ31);
			}
			for(int a=0;a<5;a++){
				for(int b=a+1;b<5;b++){
				ArrayList<Integer[][][]> templ32=new ArrayList<Integer[][][]>();
					for(int c=0;c<5;c++){
						if(c<b+1){
							templ32.add(null);
						}else{
							Integer[][][] tempInt3=new Integer[data.get(a).size()][data.get(b).size()][data.get(c).size()];
							for(int d=0;d<length;d++){
								if(tempInt3[appear[d][a]][appear[d][b]][appear[d][c]]==null){
									tempInt3[appear[d][a]][appear[d][b]][appear[d][c]]=0;
								}
								tempInt3[appear[d][a]][appear[d][b]][appear[d][c]]++;
							}
							templ32.add(tempInt3);
						}
					}
				l3.get(a).set(b, templ32);
				}
			}
			int len=0;
			for(int a=0;a<5;a++){
				for(int b=a+1;b<5;b++){
					for(int c=b+1;c<5;c++){
						for(int d=0;d<l3.get(a).get(b).get(c).length;d++){
							for(int e=0;e<l3.get(a).get(b).get(c)[d].length;e++){
								len+=l3.get(a).get(b).get(c)[d][e].length;
							}
						}
					}
				}
			}
			System.out.println("lenDone");
			int[] t1=new int[len];
			int[] t2=new int[len];
			int[] t3=new int[len];
			int[] t1v=new int[len];
			int[] t2v=new int[len];
			int[] t3v=new int[len];
			int[] l3val=new int[len];
			int head=0;
			for(int a=0;a<5;a++){
				for(int b=a+1;b<5;b++){
					for(int c=b+1;c<5;c++){
						for(int d=0;d<l3.get(a).get(b).get(c).length;d++){
							for(int f=0;f<l3.get(a).get(b).get(c)[d].length;f++){
									for(int g=0;g<l3.get(a).get(b).get(c)[d][f].length;g++){
									if(l3.get(a).get(b).get(c)[d][f][g]==null){
										l3val[head]=0;
									}else{
										l3val[head]=l3.get(a).get(b).get(c)[d][f][g];
									}
									t1[head]=a;
									t2[head]=b;
									t3[head]=c;
									t1v[head]=d;
									t2v[head]=f;
									t3v[head]=g;
									head++;
								}
							}
						}
					}
				}
			}
			System.out.println("headDone");
			ArrayList<ArrayList<Integer>> struct=new ArrayList<ArrayList<Integer>>();
			ArrayList<Integer> integ=new ArrayList<Integer>();
			for(int a=0;a<len;a++){
				tempIndex=integ.indexOf(l3val[a]);
				if(tempIndex==-1){
					integ.add(l3val[a]);
					ArrayList<Integer> tempStruct=new ArrayList<Integer>();
					tempStruct.add(a);
					struct.add(tempStruct);
				}else{
					struct.get(tempIndex).add(a);
				}
			}
			for (int i = integ.size()-1; i > 0; --i){
	            for (int j = 0; j < i; ++j){
	                if (integ.get(j) < integ.get(j+1)){
	                    Integer tmpInt=integ.get(j);
	                    integ.set(j, integ.get(j+1));
	                    integ.set(j+1, tmpInt);
	                    ArrayList<Integer> tmpIntList=struct.get(j);
	                    struct.set(j, struct.get(j+1));
	                    struct.set(j+1, tmpIntList);
	                }
	            }
			}
			System.out.println("sortDone");
			for(int b=0, a=0, c=0;b<len&&c<k;b++){
				for(int d=0;d<struct.get(b).size();d++,c++){
					a=struct.get(b).get(d);
					writer.println(keys[t1[a]]+":"+data.get(t1[a]).get(t1v[a])+","+keys[t2[a]]+":"+data.get(t2[a]).get(t2v[a])+";"+","+keys[t3[a]]+":"+data.get(t3[a]).get(t3v[a])+";"+l3val[a]);
				}
				
			}
			writer.close();
			System.out.println("done");
		}
		if(l==4){
			int[][] l4order={{0, 1, 2, 3}, {0, 1, 2, 4}, {0, 1, 3, 4}, {0, 2, 3, 4}, {1, 2, 3, 4}};
			ArrayList<Integer[][][][]> l4;
			l4 =new ArrayList<Integer[][][][]>();
			for(int a=0;a<5;a++){
				l4.add(new Integer[data.get(l4order[a][0]).size()][data.get(l4order[a][1]).size()][data.get(l4order[a][2]).size()][data.get(l4order[a][3]).size()]);
			}
			
			for(int a=0;a<5;a++){
				for(int b=0;b<length;b++){
					if(l4.get(a)[appear[b][l4order[a][0]]][appear[b][l4order[a][1]]][appear[b][l4order[a][2]]][appear[b][l4order[a][3]]]==null){
						l4.get(a)[appear[b][l4order[a][0]]][appear[b][l4order[a][1]]][appear[b][l4order[a][2]]][appear[b][l4order[a][3]]]=0;
					}
					l4.get(a)[appear[b][l4order[a][0]]][appear[b][l4order[a][1]]][appear[b][l4order[a][2]]][appear[b][l4order[a][3]]]++;
				}
			}
				
			int len=0;
			for(int a=0;a<5;a++){
				for(int b=0;b<l4.get(a).length;b++){
					for(int c=0;c<l4.get(a)[b].length;c++){
						for(int d=0;d<l4.get(a)[b][c].length;d++){
							len+=l4.get(a)[b][c][d].length;
						}
					}
				}
			}
			System.out.println("lenDone");
			int[] elorder=new int[len];
			int[] t1v=new int[len];
			int[] t2v=new int[len];
			int[] t3v=new int[len];
			int[] t4v=new int[len];
			int[] l4val=new int[len];
			int head=0;
			for(int a=0;a<5;a++){
				for(int b=0;b<l4.get(a).length;b++){
					for(int c=0;c<l4.get(a)[b].length;c++){
						for(int d=0;d<l4.get(a)[b][c].length;d++){
							for(int e=0;e<l4.get(a)[b][c][d].length;e++){
								elorder[head]=a;
								t1v[head]=b;
								t2v[head]=c;
								t3v[head]=d;
								t4v[head]=e;
								if(l4.get(a)[b][c][d][e]==null){
									l4val[head]=0;
								}else{
									l4val[head]=l4.get(a)[b][c][d][e];
								}
								head++;
							}
						}
					}
				}
			}
			System.out.println("headDone");
			ArrayList<ArrayList<Integer>> struct=new ArrayList<ArrayList<Integer>>();
			ArrayList<Integer> integ=new ArrayList<Integer>();
			for(int a=0;a<len;a++){
				tempIndex=integ.indexOf(l4val[a]);
				if(tempIndex==-1){
					integ.add(l4val[a]);
					ArrayList<Integer> tempStruct=new ArrayList<Integer>();
					tempStruct.add(a);
					struct.add(tempStruct);
				}else{
					struct.get(tempIndex).add(a);
				}
			}
			for (int i = integ.size()-1; i > 0; --i){
	            for (int j = 0; j < i; ++j){
	                if (integ.get(j) < integ.get(j+1)){
	                    Integer tmpInt=integ.get(j);
	                    integ.set(j, integ.get(j+1));
	                    integ.set(j+1, tmpInt);
	                    ArrayList<Integer> tmpIntList=struct.get(j);
	                    struct.set(j, struct.get(j+1));
	                    struct.set(j+1, tmpIntList);
	                }
	            }
			}
			System.out.println("sortDone");
			for(int b=0, a=0, c=0;b<len&&c<k;b++){
				for(int d=0;d<struct.get(b).size();d++,c++){
					a=struct.get(b).get(d);
					writer.println(keys[l4order[elorder[a]][0]]+":"+data.get(l4order[elorder[a]][0]).get(t1v[a])+","+keys[l4order[elorder[a]][1]]+":"+data.get(l4order[elorder[a]][1]).get(t2v[a])+","+keys[l4order[elorder[a]][2]]+":"+data.get(l4order[elorder[a]][2]).get(t3v[a])+","+keys[l4order[elorder[a]][3]]+":"+data.get(l4order[elorder[a]][3]).get(t4v[a])+";"+l4val[a]);
				}
				
			}
			writer.close();
			System.out.println("done");
		}
		System.out.println("End");
	}
	
	public static JSONArray getJSONFromURL(String url) throws Exception{
		URL website = new URL(url);
        URLConnection connection = website.openConnection();
		JSONArray json=new JSONArray(new JSONTokener(connection.getInputStream()));
		return json;
    }
	
	public static int indexOf(String pattern, String s) {
	    Matcher matcher = Pattern.compile(pattern).matcher(s);
	    return matcher.find() ? matcher.start() : -1;
	}
}
