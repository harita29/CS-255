#!/usr/bin/python

import random
import argparse


def generate_matrix(number):
    matrices = []
    for _ in range(number):
        matrices.append(random.randrange(10, 100))
    return matrices

def print_matrices(matrices):
    print (len(matrices) - 1)
    for x in range(len(matrices)-1):
        print str(matrices[x]) + " " + str(matrices[x+1])

#print_matrices(generate_matrix(20))

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument('--number', help='Total Matrice')
    args = parser.parse_args()
    print_matrices(generate_matrix(int(args.number) + 1))
