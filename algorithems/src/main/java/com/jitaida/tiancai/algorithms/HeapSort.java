package com.jitaida.tiancai.algorithms;

public class HeapSort {
    /**
     * ����
     *
     * @param arrays          ��������ȫ������
     * @param currentRootNode ��ǰ���ڵ�λ��
     * @param size            �ڵ�����
     */
    public static void heapify(int[] arrays, int currentRootNode, int size) {

        if (currentRootNode < size) {
            //����������������λ��
            int left = 2 * currentRootNode + 1;
            int right = 2 * currentRootNode + 2;

            //�ѵ�ǰ���ڵ�λ�ÿ���������
            int max = currentRootNode;

            if (left < size) {
                //����ȵ�ǰ��Ԫ��Ҫ�󣬼�¼����λ��
                if (arrays[max] < arrays[left]) {
                    max = left;
                }
            }
            if (right < size) {
                //����ȵ�ǰ��Ԫ��Ҫ�󣬼�¼����λ��
                if (arrays[max] < arrays[right]) {
                    max = right;
                }
            }
            //������Ĳ��Ǹ�Ԫ��λ�ã���ô�ͽ���
            if (max != currentRootNode) {
                int temp = arrays[max];
                arrays[max] = arrays[currentRootNode];
                arrays[currentRootNode] = temp;

                //�����Ƚϣ�ֱ�����һ�ν���
                heapify(arrays, max, size);
            }
        }
    }

    /**
     * ���һ�ν��ѣ����ֵ�ڶѵĶ���(���ڵ�)
     */
    public static void maxHeapify(int[] arrays, int size) {

        // �������β����ʼ��ֱ����һ��Ԫ��(�Ǳ�Ϊ0)
        for (int i = size - 1; i >= 0; i--) {
            heapify(arrays, i, size);
        }

    }

    public static void main(String [] args){
        int[] arr = {1,3,4,6,79,2,3,1,7,5,5,78,2,13,12,1,5,7};
        HeapSort.maxHeapify(arr, arr.length);

        for(int e: arr){
            System.out.print(" " + e);
        }
    }
}
