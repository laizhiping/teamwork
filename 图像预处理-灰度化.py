# -- coding: utf-8 --
import cv2
#灰度化

Image = cv2.imread("5.jpg")
GrayImage = cv2.cvtColor(Image, cv2.COLOR_BGR2GRAY)    #灰度化
cv2.imshow("Image", Image)
cv2.imshow("GrayImage", GrayImage)
cv2.waitKey(0)
cv2.destroyAllWindows()