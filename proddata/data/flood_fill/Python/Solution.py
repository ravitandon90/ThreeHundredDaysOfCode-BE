class Solution:

    def color(self, image, i, j, color, source):
        if((i >= 0 and i < len(image)) and (j >= 0 and j < len(image[0]))):

            if(image[i][j] == source):
                image[i][j] = color
                self.color(image, i, j+1, color, source)
                self.color(image, i, j-1, color, source)
                self.color(image, i+1, j, color, source)
                self.color(image, i-1, j, color, source)

    def floodFill(self, image, sr: int, sc: int, color: int):

        if(image[sr][sc] == color):
            return image

        source = image[sr][sc]
        self.color(image, sr, sc, color, source)

        return image
