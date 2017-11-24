# -- coding: utf-8 --
import cv2
import numpy as np
#分割字符归一化


def CutImage(image):    #分割字符归一化
    height, width = image.shape
    w = width
    h = height
    root_path = "G:\Users\2\jpg"
    cut_img = []
    left = []
    right = []
    count = -1
    count1 = 0
    upordown = True
    leftorright = True
    for i in range(h - 1):
        count += 1
        sign = False
        if(upordown):
            for j in range(w - 1):
                if(image[i, j] == 0):
                    sign = True
            if(sign):
                up = i
                upordown = False
        if(not upordown):
            for j in range(w - 1):
                if(image[i, j] == 0):
                    sign = True
            if(not sign):
                down = i
                upordown = True
                for m in range(w - 1):
                    sign = False
                    if(leftorright):
                        for n in range(up,down):
                            if(image[n, m] == 0):
                                sign = True
                        if(sign):
                            left = m
                            leftorright = False
                    if(not leftorright):
                        for n in range(up,down):
                            if(image[n, m] == 0):
                                sign = True
                        if(not sign):
                            right = m
                            leftorright = True
                            cut = image[up-1:down+1, left-1:right+1]
                            cut_img.append(cut)
                            cv2.imencode('.jpg', cut_img[count1])[1].tofile('jpg/'+ str(count1)+'.jpg')
                            count1 += 1