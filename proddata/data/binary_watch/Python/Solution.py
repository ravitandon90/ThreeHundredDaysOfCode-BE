class Solution:
    def readBinaryWatch(self, turnedOn: int):

        setbits2time = {i: [] for i in range(10+1)}
        for hr in range(0, 11+1):
            hour = f'{hr}'
            for mi in range(0, 59+1):
                if mi < 10:
                    minutes = f'0{mi}'
                else:
                    minutes = f'{mi}'

                totalBitsSet = bin(hr).count("1") + bin(mi).count("1")
                setbits2time[totalBitsSet].append(hour+":"+minutes)

        return setbits2time[turnedOn]
