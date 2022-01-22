#!/usr/bin/env python3

import os
import socket
import sys
import warnings

HOST = 'localhost'  # Standard loopback interface address (localhost)
PORT = 5678        # Port to listen on (non-privileged ports are > 1023)

sys.stdout = sys.stderr = open('/var/log/teachsmart', 'a')
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '3' # Disables tensorflow spamflow

from nudity import Nudity


with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.bind((HOST, PORT))
    s.listen()
    while True:
        conn, addr = s.accept()
        with conn:
            print('Connected by', addr)
            while True:
                data = conn.recv(1024).strip()
                if not data:
                    break
                else:
                    print("Received", data)
                    image = data.decode()
                    nudity = Nudity()
                    if nudity.has(image):
                        print("Porn")
                        conn.sendall(b'1')
                    else:
                        print("Clear")
                        conn.sendall(b'0')
        sys.stdout.flush()
