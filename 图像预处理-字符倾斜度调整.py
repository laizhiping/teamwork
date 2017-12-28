# -- coding: utf-8 --
import cv2
import numpy as np
#字符倾斜度调整

def SlopeAdjust(image):
    iSlope = np.zeros(image.shape, np.uint8)
    height, width = image.shape
    h = height
    w = width
    counts = 0
    upaver = 0.0
    downaver = 0.0
    for i in range(h / 2 - 1):
        for j in range(w - 1):
            if (image[i, j] == 0):
                counts += h / 2 - i
                upaver += j * (h / 2 - i)
    upaver /= counts
    counts = 0
    for i in range(h / 2, h - 1):
        for j in range(w - 1):
            if(image[i, j] == 0):
                counts += h - i
                downaver += j * (h - i)
    downaver /= counts
    slope = (upaver - downaver) / (w / 2)
    for i in range(h - 1):
        for j in range(w - 1):
            iSlope[i, j] = 255
    for i in range(h - 1):
        for j in range(w - 1):
            j_src =  int(((i - h / 2) - j) * slope)
            if (j_src < 0 or j_src >= h):
                iSlope[0, 0] = 255
            else:
                iSlope[i, j_src] = image[i, j]
    return iSlope

def SlopeAdjust(image):
    iSlope = np.zeros(image.shape, np.uint8)
    height, width = image.shape
    h = height
    w = width
    counts = 0
    leftaver = 0.0
    rightaver = 0.0
    for i in range(h - 1):
        for j in range(w / 2 - 1):
            if(image[i, j] == 0):
                counts += w / 2 - j
                leftaver += i * (w / 2 - j)
    leftaver /= counts
    counts = 0
    for i in range(h - 1):
        for j in range(w / 2, w - 1):
            if(image[i, j] == 0):
                counts += w - j
                rightaver += i * (w - j)
    rightaver /= counts
    slope = (leftaver - rightaver) / (w / 2)
    for i in range(h - 1):
        for j in range(w - 1):
            iSlope[i, j] = 255
    for i in range(h - 1):
        for j in range(w - 1):
            i_src = int(i - (j - w / 2) * slope)
            if (i_src < 0 or i_src >= h):
                iSlope[0, 0] = 255
            else:
                iSlope[i_src, j] = image[i, j]
    return iSlope